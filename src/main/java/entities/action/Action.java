package entities.action;

public abstract class Action {
    public final ActionType actionType;

    public Action(ActionType actionType) {
        this.actionType = actionType;
    }
}