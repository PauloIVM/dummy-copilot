package infra.shortcutsfile;
import java.util.ArrayList;

import adapters.interfaces.IShortcutsFileParser;
import adapters.key_id_adapter.KeyIdAdapter;
import entities.click_type.ClickType;
import entities.key_event.KeyEvent;
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
            e.printStackTrace();
            return null;
        }
    }

    private ArrayList<Shortcut> createShortcuts(ShortcutFile[] shortcutsFile) {
        ArrayList<Shortcut> shortcuts = new ArrayList<Shortcut>();
        if (shortcutsFile == null) { return shortcuts; }
        for (int i = 0; i < shortcutsFile.length; i++) {
            Shortcut shortcut = new Shortcut();
            shortcut.setTrigger(this.parseStringToKeyEvent(shortcutsFile[i].trigger));
            for (int j = 0; j < shortcutsFile[i].actions.length; j++) {
                ShortcutFileAction action = shortcutsFile[i].actions[j];
                if (action.type.equals("sequence") && action.keys != null) {
                    int repeat = action.repeat != null ? action.repeat : 1;
                    shortcut.addAction(repeat, this.parseStringToKeyEvent(action.keys));
                }
                if (action.type.equals("paste") && action.content != null) {
                    shortcut.addAction(action.content);
                }
            }
            shortcuts.add(shortcut);
        }
        return shortcuts;
    }

    // TODO: O nome está ruim... ele retorna um arraylist, e nao um keyEvent. Melhor:
    // 'parseStringToKeyEventList' ou algo do tipo.
    private ArrayList<KeyEvent> parseStringToKeyEvent(String keyEventRaw) {
        ArrayList<KeyEvent> keyEvent = new ArrayList<KeyEvent>();
        String[] splittedBySpace = keyEventRaw.split(" ");
        for (int index = 0; index < splittedBySpace.length; index++) {
            if (splittedBySpace[index].isEmpty()) { continue; }
            // TODO: Esse código está meio ruim. Aparentemente eu posso simplesmente eliminar
            // esse if a seguir. Criar testes automatizados e melhorar. 
            if (splittedBySpace[index].length() == 1) {
                Integer keyCode = KeyIdAdapter.parseTextToKeyId(splittedBySpace[index]);
                keyEvent.add(new KeyEvent(keyCode, ClickType.DOWN));
                keyEvent.add(new KeyEvent(keyCode, ClickType.UP));
                continue;
            }
            String[] splittedByPlusChar = splittedBySpace[index].split("\\+");
            for (int j = 0; j < splittedByPlusChar.length; j++) {
                Integer keyCode = KeyIdAdapter.parseTextToKeyId(splittedByPlusChar[j]);
                keyEvent.add(new KeyEvent(keyCode, ClickType.DOWN));
            }
            for (int j = 0; j < splittedByPlusChar.length; j++) {
                Integer keyCode = KeyIdAdapter.parseTextToKeyId(splittedByPlusChar[j]);
                keyEvent.add(new KeyEvent(keyCode, ClickType.UP));
            }
        }
        return keyEvent;
    }
}
