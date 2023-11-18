package entities.shortcut;

import java.util.ArrayList;
import entities.action.Action;
import entities.action.ActionPaste;
import entities.action.ActionSequence;
import entities.clickType.ClickType;
import entities.keyEvent.KeyEvent;

public class Shortcut {
    public final ArrayList<KeyEvent> trigger;
    public final ArrayList<Action> actions;
    public Shortcut() {
        this.trigger = new ArrayList<KeyEvent>();
        this.actions = new ArrayList<Action>();
    }

    public Shortcut addTriggerKey(Integer keyId, ClickType clickType) {
        this.trigger.add(new KeyEvent(keyId, clickType));
        return this;
    }

    public Shortcut setTrigger(ArrayList<KeyEvent> trigger) {
        this.trigger.clear();
        this.trigger.addAll(trigger);
        return this;
    }

    public Shortcut addAction(String content) {
        this.actions.add(new ActionPaste(content));
        return this;
    }

    public Shortcut addAction(Integer repeat, KeyEvent ...keyEvents) {
        this.actions.add(new ActionSequence(repeat, this.buildKeysSequence(keyEvents)));
        return this;
    }

    public Shortcut addAction(Integer repeat, ArrayList<KeyEvent> keyEvents) {
        this.actions.add(new ActionSequence(repeat, keyEvents));
        return this;
    }

    private ArrayList<KeyEvent> buildKeysSequence(KeyEvent ...keyEvents) {
        ArrayList<KeyEvent> keysSequence = new ArrayList<KeyEvent>();
        for (KeyEvent keyEvent: keyEvents) {
            keysSequence.add(keyEvent);
        }
        return keysSequence;
    }
}
