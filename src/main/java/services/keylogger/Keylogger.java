package services.keylogger;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseInputListener;
import repositories.ShortcutKeyIdAdapter;
import services.shortcuts.KeyIdAdapter;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Keylogger implements NativeKeyListener, NativeMouseInputListener {
    private KeyIdAdapter keyIdAdapter;
    private ShortcutKeyIdAdapter shortcutKeyIdAdapter;
    private Consumer<String> onKeyPressed;

    public Keylogger(Consumer<String> onKeyPressed) throws Exception {
        this.keyIdAdapter = new KeyIdAdapter();
        this.shortcutKeyIdAdapter = new ShortcutKeyIdAdapter();
        this.onKeyPressed = onKeyPressed;
    }

    public void init() {
        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.OFF);
        try {
            GlobalScreen.registerNativeHook();
            GlobalScreen.addNativeKeyListener(this);
            GlobalScreen.addNativeMouseListener(this);
            GlobalScreen.addNativeMouseMotionListener(this);
        } catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());
            System.exit(1);
        }
    }

    public void stop() {
        try {
            GlobalScreen.removeNativeKeyListener(this);
            GlobalScreen.removeNativeMouseListener(this);
            GlobalScreen.removeNativeMouseMotionListener(this);
            GlobalScreen.unregisterNativeHook();
        } catch (NativeHookException ex) {
            System.err.println("There was a problem unregistering the native hook.");
            System.err.println(ex.getMessage());
            System.exit(1);
        }
    }

    public void nativeKeyPressed(NativeKeyEvent e) {
        String key = this.shortcutKeyIdAdapter.parseKeyIdToString(
            this.keyIdAdapter.parseShortcutKeyIdToNativeKeyId(e.getKeyCode())
        );
        this.onKeyPressed.accept(key);
    }

    public void nativeKeyReleased(NativeKeyEvent e) {}
    public void nativeKeyTyped(NativeKeyEvent e) {}
    public void nativeMouseClicked(NativeMouseEvent e) {}
    public void nativeMousePressed(NativeMouseEvent e) {}
    public void nativeMouseReleased(NativeMouseEvent e) {}
    public void nativeMouseMoved(NativeMouseEvent e) {}
    public void nativeMouseDragged(NativeMouseEvent e) {}
}
