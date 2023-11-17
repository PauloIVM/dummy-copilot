package adapters.interfaces;

import java.util.function.Consumer;

import entities.key_event.KeyEvent;

public interface IKeylistenner {
    IKeylistenner setOnKeyPressedMethod(Consumer<KeyEvent> onKeyPressed);
    IKeylistenner setOnKeyReleasedMethod(Consumer<KeyEvent> onKeyReleased);
    IKeylistenner setOnKeyTypedMethod(Consumer<KeyEvent> onKeyTyped);
    void init();
    void stop();
}
