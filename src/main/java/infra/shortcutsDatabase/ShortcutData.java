package infra.shortcutsDatabase;

import adapters.interfaces.IShortcutData;
import adapters.interfaces.IShortcutDataAction;

public class ShortcutData implements IShortcutData {
    public String trigger;
    public ShortcutDataAction[] actions;

    public ShortcutData(String trigger, ShortcutDataAction[] actions) {
        this.trigger = trigger;
        this.actions = actions;
    }

    public String getTrigger() { return this.trigger; }
    public void setTrigger(String trigger) { this.trigger = trigger; }
    public IShortcutDataAction[] getActions() { return this.actions; }
    public void setActions(IShortcutDataAction[] actions) {
        this.actions = (ShortcutDataAction[]) actions;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ShortcutData)) return false;
        ShortcutData o = (ShortcutData) obj;
        for (int i = 0; i < o.actions.length; i++) {
            if (!o.actions[i].equals(this.actions[i])) {
                return false;
            }
        }
        return o.trigger.equals(this.trigger);
    }
}

