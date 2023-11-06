package entities.shortcut;

public abstract class ShortcutAction {
    public final ShortcutActionType actionType;

    public ShortcutAction(ShortcutActionType actionType) {
        this.actionType = actionType;
    }
}
