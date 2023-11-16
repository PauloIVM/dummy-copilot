package repositories;
import java.util.ArrayList;
import entities.shortcut.Shortcut;
import entities.shortcut.ShortcutClickType;
import entities.shortcut.ShortcutKeyEvent;
import entities.shortcutfile.ShortcutFile;
import entities.shortcutfile.ShortcutFileAction;
import utils.KeyIdAdapter;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

// TODO: 'mouseMode'. Para fazer a feature do 'mouseMode' de uma forma legal, acho q a
// solução seria algo do tipo:
//      - (ctrl+space m) -> action do tipo que ativa um 'mode' (camada), poderia ser:
// {
//     "trigger": "ctrl+space m",
//     "actions": [
//         { "type": "sequence", "keys": "backspace" },
//         { "type": "setMode", "mode": "mouse" }
//     ]
// }
//
// E depois, um outro evento com um "when", que só é executado em um determinado "mode". Os
// demais eventos, sem o "when", deverão ser executados em qualquer modo.
//
// {
//     "trigger": "left",
//     "when": { "mode": "mouse" },
//     "actions": [
//         { "type": "sequence", "keys": "right" },
//         { "type": "mouseMove", "direction": "left", "px": 10 }
//     ]
// }

public class ShortcutsFileParser {

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
    private ArrayList<ShortcutKeyEvent> parseStringToKeyEvent(String keyEventRaw) {
        ArrayList<ShortcutKeyEvent> keyEvent = new ArrayList<ShortcutKeyEvent>();
        String[] splittedBySpace = keyEventRaw.split(" ");
        for (int index = 0; index < splittedBySpace.length; index++) {
            if (splittedBySpace[index].isEmpty()) { continue; }
            // TODO: Esse código está meio ruim. Aparentemente eu posso simplesmente eliminar
            // esse if a seguir. Criar testes automatizados e melhorar. 
            if (splittedBySpace[index].length() == 1) {
                Integer keyCode = KeyIdAdapter.parseTextToShortcutKeyId(splittedBySpace[index]);
                keyEvent.add(new ShortcutKeyEvent(keyCode, ShortcutClickType.DOWN));
                keyEvent.add(new ShortcutKeyEvent(keyCode, ShortcutClickType.UP));
                continue;
            }
            String[] splittedByPlusChar = splittedBySpace[index].split("\\+");
            for (int j = 0; j < splittedByPlusChar.length; j++) {
                Integer keyCode = KeyIdAdapter.parseTextToShortcutKeyId(splittedByPlusChar[j]);
                keyEvent.add(new ShortcutKeyEvent(keyCode, ShortcutClickType.DOWN));
            }
            for (int j = 0; j < splittedByPlusChar.length; j++) {
                Integer keyCode = KeyIdAdapter.parseTextToShortcutKeyId(splittedByPlusChar[j]);
                keyEvent.add(new ShortcutKeyEvent(keyCode, ShortcutClickType.UP));
            }
        }
        return keyEvent;
    }
}
