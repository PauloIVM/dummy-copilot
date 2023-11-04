import java.util.logging.Level;
import java.util.logging.Logger;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseInputListener;

public class Main implements NativeKeyListener, NativeMouseInputListener {
    public Main() {}

    public static void main(String[] args) {
        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.OFF);
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());
            System.exit(1);
        }
        Main test = new Main();
        GlobalScreen.addNativeKeyListener(test);
        GlobalScreen.addNativeMouseListener(test);
        GlobalScreen.addNativeMouseMotionListener(test);
    }

    public void nativeKeyPressed(NativeKeyEvent e) {
        System.out.println("Key Pressed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
        // if (e.getKeyCode() == NativeKeyEvent.VC_ESCAPE) {
        //     try {
        //         GlobalScreen.unregisterNativeHook();
        //     } catch (NativeHookException e1) {
        //         // TODO Auto-generated catch block
        //         e1.printStackTrace();
        //     }
        // }
    }

    public void nativeKeyReleased(NativeKeyEvent e) {
        System.out.println("Key Released: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
    }

    public void nativeKeyTyped(NativeKeyEvent e) {}

    @Override
    public void nativeMouseClicked(NativeMouseEvent e) {}

    @Override
    public void nativeMousePressed(NativeMouseEvent e) {}

    @Override
    public void nativeMouseReleased(NativeMouseEvent e) {}

    @Override
    public void nativeMouseMoved(NativeMouseEvent e) {}

    @Override
    public void nativeMouseDragged(NativeMouseEvent e) {}
}
