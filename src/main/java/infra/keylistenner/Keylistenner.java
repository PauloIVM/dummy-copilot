package infra.keylistenner;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import adapters.interfaces.IKeylistenner;
import entities.clickType.ClickType;
import entities.keyEvent.KeyEvent;

import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Keylistenner implements NativeKeyListener, IKeylistenner {
    private Consumer<KeyEvent> onKeyPressed;
    private Consumer<KeyEvent> onKeyReleased;
    private Consumer<KeyEvent> onKeyTyped;

    public Keylistenner() {}

    public Keylistenner setOnKeyPressedMethod(Consumer<KeyEvent> onKeyPressed) {
        this.onKeyPressed = onKeyPressed;
        return this;
    }

    public Keylistenner setOnKeyReleasedMethod(Consumer<KeyEvent> onKeyReleased) {
        this.onKeyReleased = onKeyReleased;
        return this;
    }

    public Keylistenner setOnKeyTypedMethod(Consumer<KeyEvent> onKeyTyped) {
        this.onKeyTyped = onKeyTyped;
        return this;
    }

    public void init() {
        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.OFF);
        try {
            GlobalScreen.registerNativeHook();
            GlobalScreen.addNativeKeyListener(this);
        } catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());
            System.exit(1);
        }
    }

    public void stop() {
        try {
            GlobalScreen.removeNativeKeyListener(this);
            GlobalScreen.unregisterNativeHook();
        } catch (NativeHookException ex) {
            System.err.println("There was a problem unregistering the native hook.");
            System.err.println(ex.getMessage());
            System.exit(1);
        }
    }

    public void nativeKeyPressed(NativeKeyEvent e) {
        if (this.onKeyPressed == null) return;
        Integer key = JNativeKeyIdAdapter.parseJNativeKeyIdToShortcutKeyId(e.getKeyCode());
        this.onKeyPressed.accept(new KeyEvent(key, ClickType.DOWN));
    }

    public void nativeKeyReleased(NativeKeyEvent e) {
        if (this.onKeyReleased == null) return;
        Integer key = JNativeKeyIdAdapter.parseJNativeKeyIdToShortcutKeyId(e.getKeyCode());
        this.onKeyReleased.accept(new KeyEvent(key, ClickType.UP));
    }

    public void nativeKeyTyped(NativeKeyEvent e) {
        if (this.onKeyTyped == null) return;
        Integer key = JNativeKeyIdAdapter.parseJNativeKeyIdToShortcutKeyId(e.getKeyCode());
        this.onKeyTyped.accept(new KeyEvent(key, null));
    }
}
