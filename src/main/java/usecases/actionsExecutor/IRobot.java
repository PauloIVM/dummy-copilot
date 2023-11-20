package usecases.actionsExecutor;

import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.StringSelection;
import entities.keyId.KeyId;

public interface IRobot {
    void keyPress(KeyId keyId);
    void keyRelease(KeyId keyId);
    void paste();
    void delay(int ms);
    void setClipData(Transferable contents);
    void setClipData(StringSelection selection);
    Transferable getClipData();
}
