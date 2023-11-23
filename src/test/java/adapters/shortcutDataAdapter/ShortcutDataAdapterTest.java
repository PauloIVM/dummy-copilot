package adapters.shortcutDataAdapter;

import java.util.ArrayList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import adapters.interfaces.IShortcutData;
import adapters.interfaces.IShortcutsDataActionFactory;
import adapters.interfaces.IShortcutsDataFactory;
import entities.clickType.ClickType;
import entities.keyEvent.KeyEvent;
import entities.keyId.KeyId;
import entities.shortcut.Shortcut;

import infra.shortcutsDatabase.ShortcutData;
import infra.shortcutsDatabase.ShortcutDataAction;
import infra.shortcutsDatabase.ShortcutsDataFactory;
import infra.shortcutsDatabase.ShortcutsDataActionFactory;

public class ShortcutDataAdapterTest {
    ShortcutData[] shortcutsData;
    ArrayList<Shortcut> shortcuts;
    IShortcutsDataFactory shortcutsDataFactory;
    IShortcutsDataActionFactory shortcutsDataActionFactory;

    @BeforeEach
    void setup() {
        this.shortcutsDataFactory = new ShortcutsDataFactory();
        this.shortcutsDataActionFactory = new ShortcutsDataActionFactory();
        ShortcutDataAction[] actions = {
            new ShortcutDataAction("sequence", 2, "a b"),
            new ShortcutDataAction("paste", "foo")
        };
        ShortcutData[] shortcutsData = {
            new ShortcutData("ctrl+space m", actions),
            new ShortcutData("ctrl space p", actions),
        };
        this.shortcutsData = shortcutsData;
        ArrayList<KeyEvent> keyEventList = new ArrayList<>();
        keyEventList.add(new KeyEvent(KeyId.VK_A, ClickType.DOWN));
        keyEventList.add(new KeyEvent(KeyId.VK_A, ClickType.UP));
        keyEventList.add(new KeyEvent(KeyId.VK_B, ClickType.DOWN));
        keyEventList.add(new KeyEvent(KeyId.VK_B, ClickType.UP));
        ArrayList<Shortcut> shortcuts = new ArrayList<>();
        shortcuts.add(
            new Shortcut()
                .addTriggerKey(KeyId.VK_CONTROL, ClickType.DOWN)
                .addTriggerKey(KeyId.VK_SPACE, ClickType.DOWN)
                .addTriggerKey(KeyId.VK_CONTROL, ClickType.UP)
                .addTriggerKey(KeyId.VK_SPACE, ClickType.UP)
                .addTriggerKey(KeyId.VK_M, ClickType.DOWN)
                .addTriggerKey(KeyId.VK_M, ClickType.UP)
                .addAction(2, keyEventList)
                .addAction("foo")
        );
        shortcuts.add(
            new Shortcut()
                .addTriggerKey(KeyId.VK_CONTROL, ClickType.DOWN)
                .addTriggerKey(KeyId.VK_CONTROL, ClickType.UP)
                .addTriggerKey(KeyId.VK_SPACE, ClickType.DOWN)
                .addTriggerKey(KeyId.VK_SPACE, ClickType.UP)
                .addTriggerKey(KeyId.VK_P, ClickType.DOWN)
                .addTriggerKey(KeyId.VK_P, ClickType.UP)
                .addAction(2, keyEventList)
                .addAction("foo")
        );
        this.shortcuts = shortcuts;
    }

    @Test
    @DisplayName("Should create shortcuts")
    void testBasicShortcutsCreation() {
        ArrayList<Shortcut> result = ShortcutsDataAdapter.toShortcuts(this.shortcutsData);
        Assertions.assertTrue(shortcuts.equals(result));
    }

    @Test
    @DisplayName("Should create shortcuts-data")
    void testBasicShortcutsDataCreation() {
        IShortcutData[] result = ShortcutsDataAdapter.toShortcutsData(
            this.shortcuts,
            this.shortcutsDataFactory,
            this.shortcutsDataActionFactory
        );
        Assertions.assertEquals(this.shortcutsData[0].trigger, result[0].getTrigger());
        Assertions.assertEquals(this.shortcutsData[0].actions[0].getRepeat(), result[0].getActions()[0].getRepeat());
        Assertions.assertEquals(this.shortcutsData[0].actions[0].getContent(), result[0].getActions()[0].getContent());
        Assertions.assertEquals(this.shortcutsData[0].actions[0].getKeys(), result[0].getActions()[0].getKeys());
        Assertions.assertEquals(this.shortcutsData[0].actions[0].getType(), result[0].getActions()[0].getType());
        Assertions.assertArrayEquals(this.shortcutsData[0].actions, result[0].getActions());
        Assertions.assertArrayEquals(this.shortcutsData, result);
    }
}
