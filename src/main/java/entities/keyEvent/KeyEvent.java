package entities.keyEvent;
import entities.clickType.ClickType;

public class KeyEvent {
    public final Integer keyId;
    public final ClickType clickType;

    public KeyEvent(Integer keyId, ClickType clickType) {
        this.keyId = keyId;
        this.clickType = clickType;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof KeyEvent)) return false;
        KeyEvent keyInput = (KeyEvent) obj;
        return keyInput.keyId == this.keyId && keyInput.clickType == this.clickType;
    }
}
