package infra.repositories.shortcutsRepository;

import java.util.ArrayList;

import adapters.keyEventListAdapter.KeyEventListAdapter;
import entities.action.Action;
import entities.action.ActionPaste;
import entities.action.ActionSequence;
import entities.action.ActionType;
import entities.shortcut.Shortcut;

public class ShortcutsDataAdapter {
    static public ArrayList<Shortcut> toShortcuts(ShortcutData[] shortcutsData) {
        ArrayList<Shortcut> shortcuts = new ArrayList<Shortcut>();
        if (shortcutsData == null) { return shortcuts; }
        for (int i = 0; i < shortcutsData.length; i++) {
            Shortcut shortcut = new Shortcut();
            shortcut.setTrigger(KeyEventListAdapter.toKeyEventList(shortcutsData[i].getTrigger()));
            for (int j = 0; j < shortcutsData[i].getActions().length; j++) {
                ShortcutDataAction action = shortcutsData[i].getActions()[j];
                if (action.getType().equals("sequence") && action.getKeys() != null) {
                    int repeat = action.getRepeat() != null ? action.getRepeat() : 1;
                    shortcut.addAction(repeat, KeyEventListAdapter.toKeyEventList(action.getKeys()));
                }
                if (action.getType().equals("paste") && action.getContent() != null) {
                    shortcut.addAction(action.getContent());
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
