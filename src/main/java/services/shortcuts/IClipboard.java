package services.shortcuts;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.StringSelection;

public interface IClipboard {
    public Transferable getContents();
    public void setContents(Transferable contents);
    public void setContents(StringSelection selection);
}
