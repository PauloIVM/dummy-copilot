package adapters.keyEventListAdapter;

import java.util.ArrayList;

import adapters.keyIdAdapter.KeyIdAdapter;
import entities.clickType.ClickType;
import entities.keyEvent.KeyEvent;
import entities.keyId.KeyId;

public class KeyEventListAdapter {
    static public ArrayList<KeyEvent> toKeyEventList(String keyEventRaw) {
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

    static public String toString(ArrayList<KeyEvent> keyEventList) {
        if (keyEventList.size() == 0) return "";
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
