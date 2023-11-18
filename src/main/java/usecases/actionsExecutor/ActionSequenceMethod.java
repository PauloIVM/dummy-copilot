package usecases.actionsExecutor;

import entities.action.ActionSequence;
import entities.clickType.ClickType;

class ActionSequenceMethod {
    static void exec(ActionSequence action, IRobot robot) {
        for (int i = 0; i < action.repeat; i++) {
            action.keysSequence.forEach(key -> {
                if (key.clickType == ClickType.DOWN) {
                    robot.keyPress(key.keyId);
                }
                if (key.clickType == ClickType.UP) {
                    robot.keyRelease(key.keyId);
                }
            });
        }
    }
}
