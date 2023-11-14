package services.shortcuts;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.StringSelection;

public class ShortcutsClipboard implements IClipboard {
    Clipboard sysClip;

    public ShortcutsClipboard() {
        this.sysClip = Toolkit.getDefaultToolkit().getSystemClipboard();
    }

    public Transferable getContents() {
        return this.sysClip.getContents(null);
    }

    public void setContents(StringSelection s) {
        this.sysClip.setContents(s, s);
    }
    
    public void setContents(Transferable t) {
        this.sysClip.setContents(t, null);
    }
}
