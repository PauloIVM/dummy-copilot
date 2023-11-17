package adapters.keylogger;

import java.util.function.Consumer;

import adapters.interfaces.IKeylistenner;
import adapters.key_id_adapter.KeyIdAdapter;
import entities.key_event.KeyEvent;

public class Keylogger {
    private Consumer<String> onKeyPressed;
    private IKeylistenner keylistenner;

    public Keylogger() {}

    public Keylogger setKeylistenner(IKeylistenner keylistenner) {
        this.keylistenner = keylistenner;
        return this;
    }

    public Keylogger setConsumer(Consumer<String> onKeyPressed) {
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
