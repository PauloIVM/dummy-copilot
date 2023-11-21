package infra.shortcutsfile;
import java.util.ArrayList;

import adapters.interfaces.IShortcutsFileParser;
import adapters.keyIdAdapter.KeyIdAdapter;
import entities.action.Action;
import entities.action.ActionPaste;
import entities.action.ActionSequence;
import entities.action.ActionType;
import entities.clickType.ClickType;
import entities.keyEvent.KeyEvent;
import entities.keyId.KeyId;
import entities.shortcut.Shortcut;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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

    protected ShortcutFile[] createShortcutsFile(ArrayList<Shortcut> shortcuts) {
        ShortcutFile[] file = new ShortcutFile[shortcuts.size()];
        for (int i = 0; i < file.length; i++) {
            Shortcut shortcut = shortcuts.get(i);
            ShortcutFileAction[] actionsFile = new ShortcutFileAction[shortcut.actions.size()];
            for (int j = 0; j < actionsFile.length; j++) {
                Action action = shortcut.actions.get(j);
                if (action.actionType == ActionType.PASTE) {
                    ActionPaste actionPaste = (ActionPaste) action;
                    actionsFile[j] = new ShortcutFileAction("paste", actionPaste.content);
                }
                if (action.actionType == ActionType.SEQUENCE) {
                    ActionSequence actionSequence = (ActionSequence) action;
                    String keysSequenceStr = this.parseKeyEventListToString(actionSequence.keysSequence);
                    actionsFile[j] = new ShortcutFileAction("sequence", actionSequence.repeat, keysSequenceStr);
                }
            }
            file[i] = new ShortcutFile(
                this.parseKeyEventListToString(shortcut.trigger),
                actionsFile
            );
        }
        return file;
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

    private String parseKeyEventListToString(ArrayList<KeyEvent> keyEventList) {
        String result = "";
        for (int index = 0; index < keyEventList.size(); index++) {
            KeyEvent keyEvent = keyEventList.get(index);
            if (index == keyEventList.size() - 1 || keyEvent.clickType == ClickType.UP) continue;
            KeyEvent nextKeyEvent = keyEventList.get(index + 1);
            if (keyEvent.keyId == nextKeyEvent.keyId || nextKeyEvent.clickType == ClickType.UP) {
                result = result.concat(KeyIdAdapter.parseKeyIdToText(keyEvent.keyId)).concat(" ");
            } else {
                result = result.concat(KeyIdAdapter.parseKeyIdToText(keyEvent.keyId)).concat("+");
            }
        }
        return result.substring(0, result.length() - 1);
    }
}
