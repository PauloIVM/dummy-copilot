package infra.keylistenner;
import java.util.HashMap;
import org.jnativehook.keyboard.NativeKeyEvent;

import entities.keyId.KeyId;

public class JNativeKeyIdAdapter {
    static private HashMap<Integer, Integer> jNativeKeyIdToshortcutKeyIdMap;
    static private Boolean hasBuilt = false;

    static public Integer parseJNativeKeyIdToShortcutKeyId(Integer shortcutKeyId) {
        if (JNativeKeyIdAdapter.hasBuilt != true) JNativeKeyIdAdapter.buildHashMaps();
        return JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.get(shortcutKeyId);
    }

    static private void buildHashMaps() {
        JNativeKeyIdAdapter.buildJNativeKeyIdToShortcutKeyIdMap();
        JNativeKeyIdAdapter.hasBuilt = true;
    }

    static private void buildJNativeKeyIdToShortcutKeyIdMap() {
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap = new HashMap<>();
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_ENTER, KeyId.VK_ENTER);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_BACKSPACE, KeyId.VK_BACK_SPACE);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_TAB, KeyId.VK_TAB);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_SHIFT_L, KeyId.VK_SHIFT);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_SHIFT_R, KeyId.VK_SHIFT);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_CONTROL_L, KeyId.VK_CONTROL);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_CONTROL_R, KeyId.VK_CONTROL);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_ALT_L, KeyId.VK_ALT);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_ALT_R, KeyId.VK_ALT);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_PAUSE, KeyId.VK_PAUSE);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_CAPS_LOCK, KeyId.VK_CAPS_LOCK);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_ESCAPE, KeyId.VK_ESCAPE);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_SPACE, KeyId.VK_SPACE);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_PAGE_UP, KeyId.VK_PAGE_UP);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_PAGE_DOWN, KeyId.VK_PAGE_DOWN);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_END, KeyId.VK_END);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_HOME, KeyId.VK_HOME);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_LEFT, KeyId.VK_LEFT);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_UP, KeyId.VK_UP);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_RIGHT, KeyId.VK_RIGHT);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_DOWN, KeyId.VK_DOWN);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_COMMA, KeyId.VK_COMMA);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_MINUS, KeyId.VK_MINUS);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_PERIOD, KeyId.VK_PERIOD);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_SLASH, KeyId.VK_SLASH);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_0, KeyId.VK_0);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_1, KeyId.VK_1);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_2, KeyId.VK_2);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_3, KeyId.VK_3);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_4, KeyId.VK_4);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_5, KeyId.VK_5);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_6, KeyId.VK_6);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_7, KeyId.VK_7);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_8, KeyId.VK_8);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_9, KeyId.VK_9);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_SEMICOLON, KeyId.VK_SEMICOLON);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_EQUALS, KeyId.VK_EQUALS);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_A, KeyId.VK_A);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_B, KeyId.VK_B);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_C, KeyId.VK_C);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_D, KeyId.VK_D);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_E, KeyId.VK_E);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_F, KeyId.VK_F);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_G, KeyId.VK_G);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_H, KeyId.VK_H);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_I, KeyId.VK_I);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_J, KeyId.VK_J);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_K, KeyId.VK_K);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_L, KeyId.VK_L);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_M, KeyId.VK_M);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_N, KeyId.VK_N);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_O, KeyId.VK_O);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_P, KeyId.VK_P);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_Q, KeyId.VK_Q);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_R, KeyId.VK_R);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_S, KeyId.VK_S);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_T, KeyId.VK_T);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_U, KeyId.VK_U);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_V, KeyId.VK_V);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_W, KeyId.VK_W);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_X, KeyId.VK_X);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_Y, KeyId.VK_Y);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_Z, KeyId.VK_Z);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_OPEN_BRACKET, KeyId.VK_OPEN_BRACKET);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_BACK_SLASH, KeyId.VK_BACK_SLASH);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_CLOSE_BRACKET, KeyId.VK_CLOSE_BRACKET);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_KP_0, KeyId.VK_NUMPAD0);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_KP_1, KeyId.VK_NUMPAD1);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_KP_2, KeyId.VK_NUMPAD2);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_KP_3, KeyId.VK_NUMPAD3);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_KP_4, KeyId.VK_NUMPAD4);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_KP_5, KeyId.VK_NUMPAD5);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_KP_6, KeyId.VK_NUMPAD6);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_KP_7, KeyId.VK_NUMPAD7);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_KP_8, KeyId.VK_NUMPAD8);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_KP_9, KeyId.VK_NUMPAD9);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_KP_MULTIPLY, KeyId.VK_MULTIPLY);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_KP_ADD, KeyId.VK_ADD);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_KP_SEPARATOR, KeyId.VK_SEPARATER);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_KP_SEPARATOR, KeyId.VK_SEPARATOR);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_KP_SUBTRACT, KeyId.VK_SUBTRACT);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_KP_DIVIDE, KeyId.VK_DIVIDE);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_DELETE, KeyId.VK_DELETE);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_NUM_LOCK, KeyId.VK_NUM_LOCK);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_SCROLL_LOCK, KeyId.VK_SCROLL_LOCK);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_F1, KeyId.VK_F1);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_F2, KeyId.VK_F2);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_F3, KeyId.VK_F3);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_F4, KeyId.VK_F4);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_F5, KeyId.VK_F5);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_F6, KeyId.VK_F6);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_F7, KeyId.VK_F7);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_F8, KeyId.VK_F8);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_F9, KeyId.VK_F9);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_F10, KeyId.VK_F10);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_F11, KeyId.VK_F11);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_F12, KeyId.VK_F12);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_F13, KeyId.VK_F13);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_F14, KeyId.VK_F14);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_F15, KeyId.VK_F15);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_F16, KeyId.VK_F16);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_F17, KeyId.VK_F17);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_F18, KeyId.VK_F18);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_F19, KeyId.VK_F19);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_F20, KeyId.VK_F20);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_F21, KeyId.VK_F21);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_F22, KeyId.VK_F22);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_F23, KeyId.VK_F23);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_F24, KeyId.VK_F24);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_PRINTSCREEN, KeyId.VK_PRINTSCREEN);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_INSERT, KeyId.VK_INSERT);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_SUN_HELP, KeyId.VK_HELP);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_META_L, KeyId.VK_META);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_META_R, KeyId.VK_META);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_BACKQUOTE, KeyId.VK_BACK_QUOTE);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_QUOTE, KeyId.VK_QUOTE);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_UP, KeyId.VK_KP_UP);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_DOWN, KeyId.VK_KP_DOWN);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_LEFT, KeyId.VK_KP_LEFT);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_RIGHT, KeyId.VK_KP_RIGHT);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_UNDERSCORE, KeyId.VK_UNDERSCORE);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_CONTEXT_MENU, KeyId.VK_CONTEXT_MENU);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_KANJI, KeyId.VK_KANJI);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_KATAKANA, KeyId.VK_KATAKANA);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_HIRAGANA, KeyId.VK_HIRAGANA);
        JNativeKeyIdAdapter.jNativeKeyIdToshortcutKeyIdMap.put(NativeKeyEvent.VC_UNDEFINED, KeyId.VK_UNDEFINED);
    }
}