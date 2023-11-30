package usecases.keyLoggerRunner;

import java.util.function.Consumer;
import usecases.interfaces.IKeylistenner;
import adapters.keyIdAdapter.KeyIdAdapter;
import entities.keyEvent.KeyEvent;

public class KeyLoggerRunner {
    private Consumer<String> onKeyPressed;
    private IKeylistenner keylistenner;

    public KeyLoggerRunner(IKeylistenner keylistenner, Consumer<String> onKeyPressed) {
        this.keylistenner = keylistenner;
        this.onKeyPressed = onKeyPressed;
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
