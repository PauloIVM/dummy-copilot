package infra.shortcutsfile;

public class ShortcutFile {
    ShortcutFile(String trigger, ShortcutFileAction[] actions) {
        this.trigger = trigger;
        this.actions = actions;
    }
    public String trigger;
    public ShortcutFileAction[] actions;
}
