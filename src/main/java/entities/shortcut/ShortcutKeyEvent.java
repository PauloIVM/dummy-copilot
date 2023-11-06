package entities.shortcut;

public class ShortcutKeyEvent {
    public final Integer keyId;
    public final ShortcutClickType clickType;

    public ShortcutKeyEvent(Integer keyId, ShortcutClickType clickType) {
        this.keyId = keyId;
        this.clickType = clickType;
    }
}
