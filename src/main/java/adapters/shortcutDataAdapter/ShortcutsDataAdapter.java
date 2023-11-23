package adapters.shortcutDataAdapter;

import java.util.ArrayList;

import adapters.interfaces.IShortcutData;
import adapters.interfaces.IShortcutDataAction;
import adapters.interfaces.IShortcutsDataActionFactory;
import adapters.interfaces.IShortcutsDataFactory;
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

    // TODO: Não sei se eu gostei dessa abordagem dessas factories soltas assim. Por outro
    // lado, se eu for concentrar elas no "ShortcutsDatabase" de infra, eu acho q eu teria
    // q mover esse adapter para dentro do "ShortcutModel", o que eu tbm não sei se é o
    // q eu quero. Pensar melhor e tomar uma decisão.
    static public IShortcutData[] toShortcutsData(
        ArrayList<Shortcut> shortcuts,
        IShortcutsDataFactory shortcutsDataFactory,
        IShortcutsDataActionFactory shortcutsDataActionFactory
    ) {
        IShortcutData[] shortcutsData = shortcutsDataFactory.createArray(shortcuts.size());
        for (int i = 0; i < shortcutsData.length; i++) {
            Shortcut shortcut = shortcuts.get(i);
            IShortcutDataAction[] actionsData = shortcutsDataActionFactory.createArray(
                shortcut.actions.size()
            );
            for (int j = 0; j < actionsData.length; j++) {
                Action action = shortcut.actions.get(j);
                if (action.actionType == ActionType.PASTE) {
                    ActionPaste actionPaste = (ActionPaste) action;
                    actionsData[j] = shortcutsDataActionFactory.createElement("paste", actionPaste.content);
                }
                if (action.actionType == ActionType.SEQUENCE) {
                    ActionSequence actionSequence = (ActionSequence) action;
                    String keysSequenceStr = KeyEventListAdapter.toString(actionSequence.keysSequence);
                    actionsData[j] = shortcutsDataActionFactory.createElement("sequence", actionSequence.repeat, keysSequenceStr);
                }
            }
            shortcutsData[i] = shortcutsDataFactory.createElement(
                KeyEventListAdapter.toString(shortcut.trigger),
                actionsData
            );
        }
        return shortcutsData;
    }
}
