package entities.shortcut;

public class ShortcutActionPaste extends ShortcutAction {
    final String content;

    ShortcutActionPaste(String content) {
        super(ShortcutActionType.PASTE);
        this.content = content;
    }
}
