package entities.shortcut;

import java.util.ArrayList;

public class Shortcut {
    public final ArrayList<ShortcutKeyEvent> trigger;
    public final ArrayList<ShortcutAction> actions;
    public Shortcut() {
        this.trigger = new ArrayList<ShortcutKeyEvent>();
        this.actions = new ArrayList<ShortcutAction>();
    }

    public Shortcut addTriggerKey(Integer keyId, ShortcutClickType clickType) {
        this.trigger.add(new ShortcutKeyEvent(keyId, clickType));
        return this;
    }

    public Shortcut setTrigger(ArrayList<ShortcutKeyEvent> trigger) {
        this.trigger.clear();
        this.trigger.addAll(trigger);
        return this;
    }

    public Shortcut addAction(String content) {
        this.actions.add(new ShortcutActionPaste(content));
        return this;
    }

    public Shortcut addAction(Integer repeat, ShortcutKeyEvent ...keyEvents) {
        this.actions.add(new ShortcutActionSequence(repeat, this.buildKeysSequence(keyEvents)));
        return this;
    }

    public Shortcut addAction(Integer repeat, ArrayList<ShortcutKeyEvent> keyEvents) {
        this.actions.add(new ShortcutActionSequence(repeat, keyEvents));
        return this;
    }

    private ArrayList<ShortcutKeyEvent> buildKeysSequence(ShortcutKeyEvent ...keyEvents) {
        ArrayList<ShortcutKeyEvent> keysSequence = new ArrayList<ShortcutKeyEvent>();
        for (ShortcutKeyEvent keyEvent: keyEvents) {
            keysSequence.add(keyEvent);
        }
        return keysSequence;
    }
}
