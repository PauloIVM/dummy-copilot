package entities.action;

public class ActionPaste extends Action {
    public final String content;

    public ActionPaste(String content) {
        super(ActionType.PASTE);
        this.content = content;
    }   
}
