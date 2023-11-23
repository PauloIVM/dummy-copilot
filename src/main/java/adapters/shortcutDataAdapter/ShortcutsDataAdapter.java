package adapters.shortcutDataAdapter;

import java.util.ArrayList;

import adapters.interfaces.IShortcutData;
import adapters.interfaces.IShortcutDataAction;
import adapters.interfaces.IShortcutsDatabase;
import adapters.keyEventListAdapter.KeyEventListAdapter;
import entities.action.Action;
import entities.action.ActionPaste;
import entities.action.ActionSequence;
import entities.action.ActionType;
import entities.shortcut.Shortcut;

public class ShortcutsDataAdapter {
    static public ArrayList<Shortcut> toShortcuts(IShortcutData[] shortcutsData) {
        ArrayList<Shortcut> shortcuts = new ArrayList<Shortcut>();
        if (shortcutsData == null) { return shortcuts; }
        for (int i = 0; i < shortcutsData.length; i++) {
            Shortcut shortcut = new Shortcut();
            shortcut.setTrigger(KeyEventListAdapter.toKeyEventList(shortcutsData[i].getTrigger()));
            for (int j = 0; j < shortcutsData[i].getActions().length; j++) {
                IShortcutDataAction action = shortcutsData[i].getActions()[j];
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

    // INFO: Eu não gostei dessa solução de propagar o ShortcutsDatabase pra cá. Parece
    // que vai na contramão de 'quebrar-em-pequenas-partes'. Mas do jeito q estava antes,
    // com várias factories correspondentes ao database em infra, eu acho que perdeu a
    // coesão e ficou extremamente confuso. Talvez valha tentar pensar em outras soluções
    // depois. Poderia quebrar esses caras em outros models por exemplo?
    static public IShortcutData[] toShortcutsData(
        ArrayList<Shortcut> shortcuts,
        IShortcutsDatabase db
    ) {
        IShortcutData[] shortcutsData = db.createShortcutDataArray(shortcuts.size());
        for (int i = 0; i < shortcutsData.length; i++) {
            Shortcut shortcut = shortcuts.get(i);
            IShortcutDataAction[] actionsData = db.createShortcutDataActionArray(
                shortcut.actions.size()
            );
            for (int j = 0; j < actionsData.length; j++) {
                Action action = shortcut.actions.get(j);
                if (action.actionType == ActionType.PASTE) {
                    ActionPaste actionPaste = (ActionPaste) action;
                    actionsData[j] = db.createShortcutDataAction("paste", actionPaste.content);
                }
                if (action.actionType == ActionType.SEQUENCE) {
                    ActionSequence actionSequence = (ActionSequence) action;
                    String keysSequenceStr = KeyEventListAdapter.toString(actionSequence.keysSequence);
                    actionsData[j] = db.createShortcutDataAction("sequence", actionSequence.repeat, keysSequenceStr);
                }
            }
            shortcutsData[i] = db.createShortcutData(
                KeyEventListAdapter.toString(shortcut.trigger),
                actionsData
            );
        }
        return shortcutsData;
    }
}
