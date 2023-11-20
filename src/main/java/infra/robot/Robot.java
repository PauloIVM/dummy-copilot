package infra.robot;

import java.awt.event.KeyEvent;

import entities.keyId.KeyId;
import usecases.actionsExecutor.IRobot;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.StringSelection;

public class Robot implements IRobot {
    java.awt.Robot awtRobot;
    Clipboard sysClip;

    public Robot() {
        try {
            this.awtRobot = new java.awt.Robot();
        } catch (Exception e) {
            System.err.println("There was a problem to use Robot.");
            System.err.println(e.getMessage());
            System.exit(1);
        }
        this.sysClip = Toolkit.getDefaultToolkit().getSystemClipboard();
    }

    public void keyPress(KeyId keyId) {
        this.awtRobot.keyPress(keyId.get());
    }

    public void keyRelease(KeyId keyId) {
        this.awtRobot.keyRelease(keyId.get());
    }

    public void paste() {
        this.awtRobot.keyPress(KeyEvent.VK_CONTROL);
        this.awtRobot.keyPress(KeyEvent.VK_V);
        this.awtRobot.keyRelease(KeyEvent.VK_CONTROL);
        this.awtRobot.keyRelease(KeyEvent.VK_V);
    }

    public void delay(int ms) {
        this.awtRobot.delay(ms);
    }

    public void setClipData(Transferable contents) {
        sysClip.setContents(contents, null);
    }

    public void setClipData(StringSelection selection) {
        sysClip.setContents(selection, selection);
    }

    public Transferable getClipData() {
        return this.sysClip.getContents(null);
    }
}
