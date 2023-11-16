package services.shortcuts;

import org.jnativehook.keyboard.NativeKeyEvent;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import entities.shortcut.Shortcut;
import entities.shortcut.ShortcutClickType;
import entities.shortcut.ShortcutKeyEvent;
import entities.shortcut.ShortcutKeyId;
import services.shortcuts_manager.ShortcutsManager;

public class ShortcutManagerTest {
    ShortcutsManager shortcutsManager;
    MockedRobot mockedRobot;

    @BeforeEach
    void setup() {
        try {
            this.mockedRobot = new MockedRobot();
            Shortcut shortcut = new Shortcut()
                .addTriggerKey(ShortcutKeyId.VK_CONTROL, ShortcutClickType.DOWN)
                .addTriggerKey(ShortcutKeyId.VK_T, ShortcutClickType.DOWN)
                .addTriggerKey(ShortcutKeyId.VK_CONTROL, ShortcutClickType.UP)
                .addTriggerKey(ShortcutKeyId.VK_T, ShortcutClickType.UP)
                .addTriggerKey(ShortcutKeyId.VK_G, ShortcutClickType.DOWN)
                .addTriggerKey(ShortcutKeyId.VK_G, ShortcutClickType.UP)
                .addAction(
                    2,
                    new ShortcutKeyEvent(ShortcutKeyId.VK_BACK_SPACE, ShortcutClickType.DOWN),
                    new ShortcutKeyEvent(ShortcutKeyId.VK_BACK_SPACE, ShortcutClickType.UP)
                );
            ArrayList<Shortcut> shortcuts = new ArrayList<Shortcut>();
            shortcuts.add(shortcut);
            this.shortcutsManager = new ShortcutsManager(mockedRobot, shortcuts);
        } catch (Exception e) {}
    }

    @Test
    @DisplayName("Should trigger shortcut")
    void testShortcutTrigger() {
        this.shortcutsManager.addKeyClicked(NativeKeyEvent.VC_CONTROL_L, ShortcutClickType.DOWN);
        this.shortcutsManager.addKeyClicked(NativeKeyEvent.VC_T, ShortcutClickType.DOWN);
        this.shortcutsManager.addKeyClicked(NativeKeyEvent.VC_CONTROL_R, ShortcutClickType.UP);
        this.shortcutsManager.addKeyClicked(NativeKeyEvent.VC_T, ShortcutClickType.UP);
        this.shortcutsManager.addKeyClicked(NativeKeyEvent.VC_G, ShortcutClickType.DOWN);
        this.shortcutsManager.addKeyClicked(NativeKeyEvent.VC_G, ShortcutClickType.UP);
        int[] expected = {
            ShortcutKeyId.VK_BACK_SPACE,
            ShortcutKeyId.VK_BACK_SPACE,
            ShortcutKeyId.VK_BACK_SPACE,
            ShortcutKeyId.VK_BACK_SPACE,
        };
        int[] robotKeysTyped = this.toIntArray(this.mockedRobot.getKeysTyped());
        assertArrayEquals(expected, robotKeysTyped);
    }

    @Test
    @DisplayName("Should trigger shortcut with previous click")
    void testShortcutTriggerWithPreviousClick() {
        this.shortcutsManager.addKeyClicked(NativeKeyEvent.VC_P, ShortcutClickType.DOWN);
        this.shortcutsManager.addKeyClicked(NativeKeyEvent.VC_P, ShortcutClickType.UP);
        this.shortcutsManager.addKeyClicked(NativeKeyEvent.VC_CONTROL_L, ShortcutClickType.DOWN);
        this.shortcutsManager.addKeyClicked(NativeKeyEvent.VC_T, ShortcutClickType.DOWN);
        this.shortcutsManager.addKeyClicked(NativeKeyEvent.VC_CONTROL_R, ShortcutClickType.UP);
        this.shortcutsManager.addKeyClicked(NativeKeyEvent.VC_T, ShortcutClickType.UP);
        this.shortcutsManager.addKeyClicked(NativeKeyEvent.VC_G, ShortcutClickType.DOWN);
        this.shortcutsManager.addKeyClicked(NativeKeyEvent.VC_G, ShortcutClickType.UP);

        this.shortcutsManager.addKeyClicked(NativeKeyEvent.VC_CONTROL_L, ShortcutClickType.DOWN);
        this.shortcutsManager.addKeyClicked(NativeKeyEvent.VC_CONTROL_L, ShortcutClickType.UP);
        this.shortcutsManager.addKeyClicked(NativeKeyEvent.VC_CONTROL_L, ShortcutClickType.DOWN);
        this.shortcutsManager.addKeyClicked(NativeKeyEvent.VC_T, ShortcutClickType.DOWN);
        this.shortcutsManager.addKeyClicked(NativeKeyEvent.VC_CONTROL_R, ShortcutClickType.UP);
        this.shortcutsManager.addKeyClicked(NativeKeyEvent.VC_T, ShortcutClickType.UP);
        this.shortcutsManager.addKeyClicked(NativeKeyEvent.VC_G, ShortcutClickType.DOWN);
        this.shortcutsManager.addKeyClicked(NativeKeyEvent.VC_G, ShortcutClickType.UP);

        int[] expected = {
            ShortcutKeyId.VK_BACK_SPACE,
            ShortcutKeyId.VK_BACK_SPACE,
            ShortcutKeyId.VK_BACK_SPACE,
            ShortcutKeyId.VK_BACK_SPACE,
            ShortcutKeyId.VK_BACK_SPACE,
            ShortcutKeyId.VK_BACK_SPACE,
            ShortcutKeyId.VK_BACK_SPACE,
            ShortcutKeyId.VK_BACK_SPACE,
        };
        int[] robotKeysTyped = this.toIntArray(this.mockedRobot.getKeysTyped());
        assertArrayEquals(expected, robotKeysTyped);
    }

    @Test
    @DisplayName("Should trigger shortcut on rising-edge")
    void testShortcutTriggerRisingEdge() {
        this.shortcutsManager.addKeyClicked(NativeKeyEvent.VC_CONTROL_L, ShortcutClickType.DOWN);
        this.shortcutsManager.addKeyClicked(NativeKeyEvent.VC_T, ShortcutClickType.DOWN);
        this.shortcutsManager.addKeyClicked(NativeKeyEvent.VC_T, ShortcutClickType.UP);
        this.shortcutsManager.addKeyClicked(NativeKeyEvent.VC_CONTROL_R, ShortcutClickType.UP);
        this.shortcutsManager.addKeyClicked(NativeKeyEvent.VC_G, ShortcutClickType.DOWN);
        this.shortcutsManager.addKeyClicked(NativeKeyEvent.VC_G, ShortcutClickType.UP);
        int[] expected = {
            ShortcutKeyId.VK_BACK_SPACE,
            ShortcutKeyId.VK_BACK_SPACE,
            ShortcutKeyId.VK_BACK_SPACE,
            ShortcutKeyId.VK_BACK_SPACE,
        };
        int[] robotKeysTyped = this.toIntArray(this.mockedRobot.getKeysTyped());
        assertArrayEquals(expected, robotKeysTyped);
    }

    @Test
    @DisplayName("Should not trigger shortcut")
    void testShortcutTriggerWithNotMatch() {
        this.shortcutsManager.addKeyClicked(NativeKeyEvent.VC_CONTROL_L, ShortcutClickType.DOWN);
        this.shortcutsManager.addKeyClicked(NativeKeyEvent.VC_T, ShortcutClickType.DOWN);
        this.shortcutsManager.addKeyClicked(NativeKeyEvent.VC_T, ShortcutClickType.UP);
        this.shortcutsManager.addKeyClicked(NativeKeyEvent.VC_CONTROL_R, ShortcutClickType.UP);

        this.shortcutsManager.addKeyClicked(NativeKeyEvent.VC_CONTROL_L, ShortcutClickType.DOWN);
        this.shortcutsManager.addKeyClicked(NativeKeyEvent.VC_CONTROL_R, ShortcutClickType.UP);
        this.shortcutsManager.addKeyClicked(NativeKeyEvent.VC_T, ShortcutClickType.DOWN);
        this.shortcutsManager.addKeyClicked(NativeKeyEvent.VC_T, ShortcutClickType.UP);
        this.shortcutsManager.addKeyClicked(NativeKeyEvent.VC_G, ShortcutClickType.DOWN);
        this.shortcutsManager.addKeyClicked(NativeKeyEvent.VC_G, ShortcutClickType.UP);

        int[] expected = {};
        int[] robotKeysTyped = this.toIntArray(this.mockedRobot.getKeysTyped());
        assertArrayEquals(expected, robotKeysTyped);
    }

    private int[] toIntArray(ArrayList<Integer> list) {
        int[] arr = new int[this.mockedRobot.getKeysTyped().size()];
        int index = 0;
        for (Integer i: this.mockedRobot.getKeysTyped()) {
            arr[index] = i;
            index++;
        }
        return arr;
    }
}
