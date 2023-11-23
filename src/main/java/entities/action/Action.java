package entities.action;

public abstract class Action {
    public final ActionType actionType;

    Action(ActionType actionType) {
        this.actionType = actionType;
    }
}
