package usecases.actionsExecutor;

import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.StringSelection;

public interface IRobot {
    void keyPress(int keycode);
    void keyRelease(int keycode);
    void paste();
    void delay(int ms);
    void setClipData(Transferable contents);
    void setClipData(StringSelection selection);
    Transferable getClipData();
}
