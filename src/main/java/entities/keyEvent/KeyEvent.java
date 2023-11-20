package entities.keyEvent;
import entities.clickType.ClickType;
import entities.keyId.KeyId;

public class KeyEvent {
    public final KeyId keyId;
    public final ClickType clickType;

    public KeyEvent(KeyId keyId, ClickType clickType) {
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
