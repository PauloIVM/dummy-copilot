package usecases.actions;

import entities.action.Action;
import entities.action.ActionPaste;
import entities.action.ActionSequence;
import entities.action.ActionType;

public class ActionsExecutor {
    private final IRobot robot;

    public ActionsExecutor(IRobot robot) {
        this.robot = robot;
    }

    public void exec(Action action) {
        if (action.actionType == ActionType.PASTE) {
            ActionPasteMethod.exec((ActionPaste) action, robot);
        }
        if (action.actionType == ActionType.SEQUENCE) {
            ActionSequenceMethod.exec((ActionSequence) action, robot);
        }
    }
}
