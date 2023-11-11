package services.shortcuts;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseInputListener;

import entities.shortcut.Shortcut;
import entities.shortcut.ShortcutActionPaste;
import entities.shortcut.ShortcutActionSequence;
import entities.shortcut.ShortcutActionType;
import entities.shortcut.ShortcutClickType;
import entities.shortcut.ShortcutKeyEvent;

import repositories.ShortcutKeyIdAdapter;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.Transferable;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ShortcutsManager implements NativeKeyListener, NativeMouseInputListener {
    private final Robot robot;
    private ArrayList<ShortcutKeyEvent> keysClicked;
    private ArrayList<Shortcut> shortcuts;
    private KeyIdAdapter keyIdAdapter;

    public ShortcutsManager(Robot robot, ArrayList<Shortcut> shortcuts) throws Exception {
        this.keysClicked = new ArrayList<>();
        this.robot = robot;
        this.shortcuts = shortcuts;
        this.keyIdAdapter = new KeyIdAdapter();
    }

    public void initListenner() {
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

    public void finishListenner() {
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
        this.onKeyPressed(e, ShortcutClickType.DOWN);
    }

    public void nativeKeyReleased(NativeKeyEvent e) {
        this.onKeyPressed(e, ShortcutClickType.UP);
    }

    public void nativeKeyTyped(NativeKeyEvent e) {}
    public void nativeMouseClicked(NativeMouseEvent e) {}
    public void nativeMousePressed(NativeMouseEvent e) {}
    public void nativeMouseReleased(NativeMouseEvent e) {}
    public void nativeMouseMoved(NativeMouseEvent e) {}
    public void nativeMouseDragged(NativeMouseEvent e) {}

    private void onKeyPressed(NativeKeyEvent e, ShortcutClickType clickType) {
        // System.out.println("NativeKeyEvent: " + e.getKeyCode());
        // System.out.println("ShortcutKeyEvent: " + this.keyIdAdapter.parseShortcutKeyIdToNativeKeyId(e.getKeyCode()));
        // ShortcutKeyIdAdapter adp = new ShortcutKeyIdAdapter();
        // System.out.println("String: " + adp.parseKeyIdToString(this.keyIdAdapter.parseShortcutKeyIdToNativeKeyId(e.getKeyCode())));
        var keyEvent = new ShortcutKeyEvent(
            this.keyIdAdapter.parseShortcutKeyIdToNativeKeyId(e.getKeyCode()),
            clickType
        );
        this.keysClicked.add(keyEvent);
        Shortcut shortcut = this.shortcuts
            .stream()
            .filter((s) -> this.hasPartialTrigger(s))
            .findFirst()
            .orElse(null);
        if (shortcut == null) {
            this.keysClicked.clear();
            this.keysClicked.add(keyEvent);
            return;
        }
        if (shortcut.trigger.size() == this.keysClicked.size()) {
            this.keysClicked.clear();
            shortcut.actions.forEach((a) -> {
                if (a.actionType == ShortcutActionType.PASTE) {
                    ShortcutActionPaste action = (ShortcutActionPaste) a;
                    StringSelection selection = new StringSelection(action.content);
                    // TODO: Inverter a dependencia do clipboard pra facilitar os testes
                    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                    Transferable currClipboardData = clipboard.getContents(null);
                    clipboard.setContents(selection, selection);
                    this.robot.keyPress(KeyEvent.VK_CONTROL);
                    this.robot.keyPress(KeyEvent.VK_V);
                    this.robot.keyRelease(KeyEvent.VK_CONTROL);
                    this.robot.keyRelease(KeyEvent.VK_V);
                    this.robot.delay(30);
                    clipboard.setContents(currClipboardData, null);
                }
                if (a.actionType == ShortcutActionType.SEQUENCE) {
                    ShortcutActionSequence action = (ShortcutActionSequence) a;
                    for (int i = 0; i < action.repeat; i++) {
                        action.keysSequence.forEach(key -> {
                            if (key.clickType == ShortcutClickType.DOWN) {
                                this.robot.keyPress(key.keyId);
                            }
                            if (key.clickType == ShortcutClickType.UP) {
                                this.robot.keyRelease(key.keyId);
                            }
                        });
                    }
                }
            });
        }
    }

    private Boolean hasPartialTrigger(Shortcut shortcut) {
        // ShortcutKeyIdAdapter adp = new ShortcutKeyIdAdapter();
        for (int i = 0; i < shortcut.trigger.size(); i++) {
            if (shortcut.trigger.size() <= i || this.keysClicked.size() <= i) {
                continue;
            }
            var triggerKey = shortcut.trigger.get(i);
            var currKeyClicked = this.keysClicked.get(i);
            if (this.isSameKey(triggerKey, currKeyClicked) == false) {
                return false;
            }
        }
        return true;
    }

    private Boolean isSameKey(ShortcutKeyEvent keyA, ShortcutKeyEvent keyB) {
        if (keyA.keyId.equals(keyB.keyId) && keyA.clickType.equals(keyB.clickType)) {
            return true;
        }
        return false;
    }
}
