package services.keylogger;

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
import services.shortcuts.KeyIdAdapter;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.Transferable;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Keylogger implements NativeKeyListener, NativeMouseInputListener {
    private KeyIdAdapter keyIdAdapter;
    private ShortcutKeyIdAdapter shortcutKeyIdAdapter;

    public Keylogger() throws Exception {
        this.keyIdAdapter = new KeyIdAdapter();
        this.shortcutKeyIdAdapter = new ShortcutKeyIdAdapter();
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
        // TODO: Aqui eu posso pensar em receber um ob "logger" por param, que define o "sout",
        // isso pro service ficar mais simples de testar e genérico. Tbm ficaria legal se eu
        // conseguisse usar uma lambda function.
        System.out.print(String.format("\033[%dA", 1));
        System.out.print("\033[2K");
        System.out.print("-> ");
        System.out.println(this.shortcutKeyIdAdapter.parseKeyIdToString(
            this.keyIdAdapter.parseShortcutKeyIdToNativeKeyId(e.getKeyCode())
        ));

    }

    public void nativeKeyReleased(NativeKeyEvent e) {}
    public void nativeKeyTyped(NativeKeyEvent e) {}
    public void nativeMouseClicked(NativeMouseEvent e) {}
    public void nativeMousePressed(NativeMouseEvent e) {}
    public void nativeMouseReleased(NativeMouseEvent e) {}
    public void nativeMouseMoved(NativeMouseEvent e) {}
    public void nativeMouseDragged(NativeMouseEvent e) {}

}
