package entities.key_event;
import entities.click_type.ClickType;

public class KeyEvent {
    public final Integer keyId;
    public final ClickType clickType;

    public KeyEvent(Integer keyId, ClickType clickType) {
        this.keyId = keyId;
        this.clickType = clickType;
    }
}
