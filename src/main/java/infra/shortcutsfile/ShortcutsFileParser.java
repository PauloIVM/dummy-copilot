package infra.shortcutsfile;
import java.util.ArrayList;

import adapters.interfaces.IShortcutsFileParser;
import adapters.keyIdAdapter.KeyIdAdapter;
import entities.clickType.ClickType;
import entities.keyEvent.KeyEvent;
import entities.keyId.KeyId;
import entities.shortcut.Shortcut;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class ShortcutsFileParser implements IShortcutsFileParser {

    public ShortcutsFileParser() {}

    public ArrayList<Shortcut> get() {
        ShortcutFile[] shortcutsFile = this.getShortcutsJsonFile();
        ArrayList<Shortcut> shortcuts = this.createShortcuts(shortcutsFile);
        return shortcuts;
    }

    protected ArrayList<Shortcut> createShortcuts(ShortcutFile[] shortcutsFile) {
        ArrayList<Shortcut> shortcuts = new ArrayList<Shortcut>();
        if (shortcutsFile == null) { return shortcuts; }
        for (int i = 0; i < shortcutsFile.length; i++) {
            Shortcut shortcut = new Shortcut();
            shortcut.setTrigger(this.parseStringToKeyEventList(shortcutsFile[i].trigger));
            for (int j = 0; j < shortcutsFile[i].actions.length; j++) {
                ShortcutFileAction action = shortcutsFile[i].actions[j];
                if (action.type.equals("sequence") && action.keys != null) {
                    int repeat = action.repeat != null ? action.repeat : 1;
                    shortcut.addAction(repeat, this.parseStringToKeyEventList(action.keys));
                }
                if (action.type.equals("paste") && action.content != null) {
                    shortcut.addAction(action.content);
                }
            }
            shortcuts.add(shortcut);
        }
        return shortcuts;
    }

    private ShortcutFile[] getShortcutsJsonFile() {
        String jsonFileName = "shortcuts.config.json";
        try {
            Gson gson = new Gson();
            BufferedReader reader = new BufferedReader(new FileReader(jsonFileName));
            JsonArray jsonArray = JsonParser.parseReader(reader).getAsJsonArray();
            ShortcutFile[] shortcuts = new ShortcutFile[jsonArray.size()];
            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
                ShortcutFile shortcut = gson.fromJson(jsonObject, ShortcutFile.class);
                shortcuts[i] = shortcut;
            }
            reader.close();
            return shortcuts;
        } catch (JsonSyntaxException | JsonIOException | IOException e) {
            System.err.println("There was a problem to read shortcuts.config.json file. Check invalid atributes.");
            System.err.println(e.getMessage());
            System.exit(1);
            return null;
        }
    }

    private ArrayList<KeyEvent> parseStringToKeyEventList(String keyEventRaw) {
        ArrayList<KeyEvent> keyEvent = new ArrayList<KeyEvent>();
        String[] splittedBySpace = keyEventRaw.split(" ");
        for (int index = 0; index < splittedBySpace.length; index++) {
            if (splittedBySpace[index].isEmpty()) { continue; }
            String[] splittedByPlusChar = splittedBySpace[index].split("\\+");
            for (int j = 0; j < splittedByPlusChar.length; j++) {
                KeyId keyId = KeyIdAdapter.parseTextToKeyId(splittedByPlusChar[j]);
                keyEvent.add(new KeyEvent(keyId, ClickType.DOWN));
            }
            for (int j = 0; j < splittedByPlusChar.length; j++) {
                KeyId keyId = KeyIdAdapter.parseTextToKeyId(splittedByPlusChar[j]);
                keyEvent.add(new KeyEvent(keyId, ClickType.UP));
            }
        }
        return keyEvent;
    }
}
