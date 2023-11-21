package infra.shortcutsfile;

public class ShortcutFile {
    public String trigger;
    public ShortcutFileAction[] actions;

    ShortcutFile(String trigger, ShortcutFileAction[] actions) {
        this.trigger = trigger;
        this.actions = actions;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ShortcutFile)) return false;
        ShortcutFile o = (ShortcutFile) obj;
        for (int i = 0; i < o.actions.length; i++) {
            if (!o.actions[i].equals(this.actions[i])) {
                return false;
            }
        }
        return o.trigger.equals(this.trigger);
    }
}
