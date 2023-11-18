package infra.composers;

import java.util.function.Consumer;
import adapters.keyloggerController.KeyloggerController;
import infra.keylistenner.Keylistenner;

public class KeyloggerControllerBuilder {
    private Consumer<String> onKeyPressed;

    public KeyloggerControllerBuilder(Consumer<String> onKeyPressed) {
        this.onKeyPressed = onKeyPressed;
    }

    public KeyloggerController build() {
        return new KeyloggerController(
            new Keylistenner(),
            this.onKeyPressed
        );
    }
}
