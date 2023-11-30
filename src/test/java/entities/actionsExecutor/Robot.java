package entities.actionsExecutor;

import java.util.ArrayList;

import entities.actionsExecutor.IRobot;
import entities.clickType.ClickType;
import entities.keyEvent.KeyEvent;
import entities.keyId.KeyId;

import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.StringSelection;

public class Robot implements IRobot {
    public StringSelection pasteOut;
    public ArrayList<KeyEvent> keysTyped;
    private StringSelection clipData;

    public Robot() {
        this.keysTyped = new ArrayList<KeyEvent>();
    }

    public void keyRelease(KeyId keyId) {
        this.keysTyped.add(new KeyEvent(keyId, ClickType.UP));
    }

    public void keyPress(KeyId keyId) {
        this.keysTyped.add(new KeyEvent(keyId, ClickType.DOWN));
    }

    public void delay(int ms) {}

    public void paste() {
        this.pasteOut = this.clipData;
    }

    public void setClipData(Transferable contents) {}

    public void setClipData(StringSelection selection) {
        this.clipData = selection;
    }

    public Transferable getClipData() {
        return null;
    }
}
