package adapters.shortcutDataAdapter;

import java.util.ArrayList;

import adapters.keyEventListAdapter.KeyEventListAdapter;
import entities.action.Action;
import entities.action.ActionPaste;
import entities.action.ActionSequence;
import entities.action.ActionType;
import entities.shortcut.Shortcut;

// TODO: Esse adapter deveria evoluir pra um model? Ou coesistir com um?
public class ShortcutsDataAdapter {
    static public ArrayList<Shortcut> toShortcuts(ShortcutData[] shortcutsData) {
        ArrayList<Shortcut> shortcuts = new ArrayList<Shortcut>();
        if (shortcutsData == null) { return shortcuts; }
        for (int i = 0; i < shortcutsData.length; i++) {
            Shortcut shortcut = new Shortcut();
            shortcut.setTrigger(KeyEventListAdapter.toKeyEventList(shortcutsData[i].trigger));
            for (int j = 0; j < shortcutsData[i].actions.length; j++) {
                ShortcutDataAction action = shortcutsData[i].actions[j];
                if (action.type.equals("sequence") && action.keys != null) {
                    int repeat = action.repeat != null ? action.repeat : 1;
                    shortcut.addAction(repeat, KeyEventListAdapter.toKeyEventList(action.keys));
                }
                if (action.type.equals("paste") && action.content != null) {
                    shortcut.addAction(action.content);
                }
            }
            shortcuts.add(shortcut);
        }
        return shortcuts;
    }

    static public ShortcutData[] toShortcutsData(ArrayList<Shortcut> shortcuts) {
        ShortcutData[] shortcutsData = new ShortcutData[shortcuts.size()];
        for (int i = 0; i < shortcutsData.length; i++) {
            Shortcut shortcut = shortcuts.get(i);
            ShortcutDataAction[] actionsData = new ShortcutDataAction[shortcut.actions.size()];
            for (int j = 0; j < actionsData.length; j++) {
                Action action = shortcut.actions.get(j);
                if (action.actionType == ActionType.PASTE) {
                    ActionPaste actionPaste = (ActionPaste) action;
                    actionsData[j] = new ShortcutDataAction("paste", actionPaste.content);
                }
                if (action.actionType == ActionType.SEQUENCE) {
                    ActionSequence actionSequence = (ActionSequence) action;
                    String keysSequenceStr = KeyEventListAdapter.toString(actionSequence.keysSequence);
                    actionsData[j] = new ShortcutDataAction("sequence", actionSequence.repeat, keysSequenceStr);
                }
            }
            shortcutsData[i] = new ShortcutData(
                KeyEventListAdapter.toString(shortcut.trigger),
                actionsData
            );
        }
        return shortcutsData;
    }
}
