package services.shortcuts_manager.actions;

import entities.shortcut.ShortcutActionPaste;
import services.shortcuts_manager.IClipboard;
import services.shortcuts_manager.IRobot;

import java.awt.event.KeyEvent;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;

public class ActionPaste {
    static void exec(ShortcutActionPaste action, IRobot robot, IClipboard clip) {
        StringSelection selection = new StringSelection(action.content);
        Transferable currClipboardData = clip.getContents();
        clip.setContents(selection);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_V);
        robot.delay(30);
        clip.setContents(currClipboardData);
    }
}
