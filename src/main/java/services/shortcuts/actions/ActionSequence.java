package services.shortcuts.actions;

import entities.shortcut.ShortcutActionSequence;
import entities.shortcut.ShortcutClickType;
import services.shortcuts.IRobot;

public class ActionSequence {
    static void exec(ShortcutActionSequence action, IRobot robot) {
        for (int i = 0; i < action.repeat; i++) {
            action.keysSequence.forEach(key -> {
                if (key.clickType == ShortcutClickType.DOWN) {
                    robot.keyPress(key.keyId);
                }
                if (key.clickType == ShortcutClickType.UP) {
                    robot.keyRelease(key.keyId);
                }
            });
        }
    }
}
