package services.shortcuts_manager.actions;

import entities.shortcut.ShortcutAction;
import entities.shortcut.ShortcutActionPaste;
import entities.shortcut.ShortcutActionSequence;
import entities.shortcut.ShortcutActionType;
import services.shortcuts_manager.IClipboard;
import services.shortcuts_manager.IRobot;

public class ActionsManager {
    private final IRobot robot;
    private final IClipboard clip;

    public ActionsManager(IRobot robot, IClipboard clip) {
        this.robot = robot;
        this.clip = clip;
    }

    public void exec(ShortcutAction action) {
        if (action.actionType == ShortcutActionType.PASTE) {
            ActionPaste.exec((ShortcutActionPaste) action, robot, clip);
        }
        if (action.actionType == ShortcutActionType.SEQUENCE) {
            ActionSequence.exec((ShortcutActionSequence) action, robot);
        }
    }
}
