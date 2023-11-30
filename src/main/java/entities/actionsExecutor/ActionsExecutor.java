package entities.actionsExecutor;

import entities.action.Action;
import entities.action.ActionPaste;
import entities.action.ActionSequence;
import entities.action.ActionType;

// TODO: Isso é uma entidade que depende de um IRobot. Em tese é até ok por termos
// invertido a dependência. Contudo, o ideal da entidade eu acho que seria que ela
// nem mesmo esperasse a 'ideia' de uma inerface. Uma maneira q eu poderia corrigir
// isso seria utilizar um pattern similar ao Observer... tipo... a entidade faz um
// subscribe de quem ela deve avisar quando uma action for disparada... acho que
// seria um refactor interessante talvez, se não complicar demais.

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
