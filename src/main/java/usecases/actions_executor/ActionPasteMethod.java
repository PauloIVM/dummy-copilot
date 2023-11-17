package usecases.actions_executor;

import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;

import entities.action.ActionPaste;

class ActionPasteMethod {
    static void exec(ActionPaste action, IRobot robot) {
        StringSelection selection = new StringSelection(action.content);
        Transferable currClipboardData = robot.getClipData();
        robot.setClipData(selection);
        robot.paste();
        robot.delay(30);
        robot.setClipData(currClipboardData);
    }
}
