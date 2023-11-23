package adapters.shortcutDataAdapter;

public class ShortcutData {
    public String trigger;
    public ShortcutDataAction[] actions;

    public ShortcutData(String trigger, ShortcutDataAction[] actions) {
        this.trigger = trigger;
        this.actions = actions;
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

