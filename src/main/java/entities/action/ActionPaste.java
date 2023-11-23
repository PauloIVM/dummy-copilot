package entities.action;

public class ActionPaste extends Action {
    public final String content;

    public ActionPaste(String content) {
        super(ActionType.PASTE);
        this.content = content;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ActionPaste)) return false;
        ActionPaste input = (ActionPaste) obj;
        return input.content.equals(this.content);
    }
}
