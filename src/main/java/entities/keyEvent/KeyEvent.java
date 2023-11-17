package entities.keyEvent;
import entities.clickType.ClickType;

public class KeyEvent {
    public final Integer keyId;
    public final ClickType clickType;

    public KeyEvent(Integer keyId, ClickType clickType) {
        this.keyId = keyId;
        this.clickType = clickType;
    }
}
