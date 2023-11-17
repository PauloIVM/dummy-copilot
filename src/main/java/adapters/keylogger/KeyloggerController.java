package adapters.keylogger;

import java.util.function.Consumer;

import adapters.interfaces.IKeylistenner;
import adapters.keyId.KeyIdAdapter;
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
