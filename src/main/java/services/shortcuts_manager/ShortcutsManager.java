package services.shortcuts_manager;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseInputListener;

import entities.shortcut.Shortcut;
import entities.shortcut.ShortcutClickType;
import entities.shortcut.ShortcutKeyEvent;
import services.shortcuts_manager.actions.ActionsManager;
import utils.KeyIdAdapter;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ShortcutsManager implements NativeKeyListener, NativeMouseInputListener {
    private final IRobot robot;
    private ArrayList<ShortcutKeyEvent> keysClicked;
    private ArrayList<Shortcut> shortcuts;
    private ActionsManager actionsManager;

    public ShortcutsManager(IRobot robot, ArrayList<Shortcut> shortcuts) {
        this.keysClicked = new ArrayList<>();
        this.robot = robot;
        this.shortcuts = shortcuts;
        this.actionsManager = new ActionsManager(this.robot, new ShortcutsClipboard());
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
        this.addKeyClicked(e.getKeyCode(), ShortcutClickType.DOWN);
    }

    public void nativeKeyReleased(NativeKeyEvent e) {
        this.addKeyClicked(e.getKeyCode(), ShortcutClickType.UP);
    }

    public void nativeKeyTyped(NativeKeyEvent e) {}
    public void nativeMouseClicked(NativeMouseEvent e) {}
    public void nativeMousePressed(NativeMouseEvent e) {}
    public void nativeMouseReleased(NativeMouseEvent e) {}
    public void nativeMouseMoved(NativeMouseEvent e) {}
    public void nativeMouseDragged(NativeMouseEvent e) {}

    public void addKeyClicked(int jNativeKeyId, ShortcutClickType clickType) {
        var keyEvent = new ShortcutKeyEvent(
            KeyIdAdapter.parseJNativeKeyIdToShortcutKeyId(jNativeKeyId),
            clickType
        );
        this.keysClicked.add(keyEvent);
        Shortcut shortcut = this.shortcuts
            .stream()
            .filter(this::hasPartialTrigger)
            .findFirst()
            .orElse(null);
        if (shortcut == null) {
            this.keysClicked.clear();
            this.keysClicked.add(keyEvent);
            return;
        }
        if (shortcut.trigger.size() == this.keysClicked.size()) {
            this.keysClicked.clear();
            shortcut.actions.forEach(this.actionsManager::exec);
        }
    }

    private Boolean hasPartialTrigger(Shortcut shortcut) {
        Boolean[] fallingEdgeMarkers = this.getFallingEdgeMarkers(shortcut.trigger);
        for (int i = 0; i < shortcut.trigger.size(); i++) {
            if (shortcut.trigger.size() <= i || this.keysClicked.size() <= i) {
                continue;
            }
            var triggerKey = shortcut.trigger.get(i);
            var currKeyClicked = this.keysClicked.get(i);
            if (i < fallingEdgeMarkers.length && fallingEdgeMarkers[i]) continue;
            if (this.isSameKey(triggerKey, currKeyClicked) == false) {
                return false;
            }
        }
        return true;
    }

    private Boolean[] getFallingEdgeMarkers(ArrayList<ShortcutKeyEvent> trigger) {
        Boolean[] fallingEdgeMarkers = new Boolean[trigger.size()];
        for (int i = 0; i < trigger.size(); i++) {
            if (trigger.get(i).clickType == ShortcutClickType.DOWN) {
                fallingEdgeMarkers[i] = false;
                continue;
            }
            ShortcutClickType UP = ShortcutClickType.UP;
            if (i == trigger.size() - 1 && trigger.get(i - 1).clickType == UP) {
                fallingEdgeMarkers[i] = true;
                continue;
            } else if (i == trigger.size() - 1) {
                fallingEdgeMarkers[i] = false;
                continue;

            }
            if (trigger.get(i - 1).clickType == UP || trigger.get(i + 1).clickType == UP) {
                fallingEdgeMarkers[i] = true;
                continue;
            }
            fallingEdgeMarkers[i] = false;

        }
        return fallingEdgeMarkers;
    }

    private Boolean isSameKey(ShortcutKeyEvent keyA, ShortcutKeyEvent keyB) {
        if (keyA.keyId.equals(keyB.keyId) && keyA.clickType.equals(keyB.clickType)) {
            return true;
        }
        return false;
    }
}
