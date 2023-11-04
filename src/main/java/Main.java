import java.util.logging.Level;
import java.util.logging.Logger;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseInputListener;

import java.awt.Robot;
import java.awt.event.KeyEvent;

public class Main implements NativeKeyListener, NativeMouseInputListener {
    private final Robot robot;
    public Main() throws Exception {
        this.robot = new Robot();
    }

    public static void main(String[] args) throws Exception {
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
        if (e.getKeyCode() == NativeKeyEvent.VC_1) {
            // https://stackoverflow.com/questions/29665534/type-a-string-using-java-awt-robot
            robot.keyPress(KeyEvent.VK_2);
            robot.keyRelease(KeyEvent.VK_2);
        }
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
