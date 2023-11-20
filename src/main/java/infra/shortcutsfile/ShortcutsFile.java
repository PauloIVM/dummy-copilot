package infra.shortcutsfile;

public class ShortcutsFile {
    ShortcutsFile(String trigger, ShortcutFileAction[] actions) {
        this.trigger = trigger;
        this.actions = actions;
    }
    public String trigger;
    public ShortcutFileAction[] actions;
}
