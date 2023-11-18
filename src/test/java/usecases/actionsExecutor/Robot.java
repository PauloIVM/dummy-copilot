package usecases.actionsExecutor;

import java.util.ArrayList;

import entities.clickType.ClickType;
import entities.keyEvent.KeyEvent;

import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.StringSelection;

public class Robot implements IRobot {
    public StringSelection pasteOut;
    public ArrayList<KeyEvent> keysTyped;
    private StringSelection clipData;

    public Robot() {
        this.keysTyped = new ArrayList<KeyEvent>();
    }

    public void keyRelease(int keycode) {
        this.keysTyped.add(new KeyEvent(keycode, ClickType.UP));
    }

    public void keyPress(int keycode) {
        this.keysTyped.add(new KeyEvent(keycode, ClickType.DOWN));
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
