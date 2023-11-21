package infra.shortcutsfile;

import java.util.ArrayList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import entities.clickType.ClickType;
import entities.keyEvent.KeyEvent;
import entities.keyId.KeyId;
import entities.shortcut.Shortcut;

public class ShortcutsFileParserTest {
    ShortcutsFileParser shortcutsFileParser;
    ShortcutFile[] shortcutsFile;
    ArrayList<Shortcut> shortcuts;

    @BeforeEach
    void setup() {
        ShortcutFileAction[] actions = {
            new ShortcutFileAction("sequence", 2, "a b"),
            new ShortcutFileAction("paste", "foo")
        };
        ShortcutFile[] shortcutsFile = {
            new ShortcutFile("ctrl+space m", actions),
            new ShortcutFile("ctrl space p", actions),
        };
        this.shortcutsFile = shortcutsFile;
        this.shortcutsFileParser = new ShortcutsFileParser();
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
        ArrayList<Shortcut> result = this.shortcutsFileParser.createShortcuts(this.shortcutsFile);
        Assertions.assertTrue(shortcuts.equals(result));
    }

    @Test
    @DisplayName("Should create shortcuts-file")
    void testBasicShortcutsFileCreation() {
        ShortcutFile[] result = this.shortcutsFileParser.createShortcutsFile(this.shortcuts);
        Assertions.assertEquals(this.shortcutsFile[0].trigger, result[0].trigger);
        Assertions.assertEquals(this.shortcutsFile[0].actions[0].repeat, result[0].actions[0].repeat);
        Assertions.assertEquals(this.shortcutsFile[0].actions[0].content, result[0].actions[0].content);
        Assertions.assertEquals(this.shortcutsFile[0].actions[0].keys, result[0].actions[0].keys);
        Assertions.assertEquals(this.shortcutsFile[0].actions[0].type, result[0].actions[0].type);
        Assertions.assertArrayEquals(this.shortcutsFile[0].actions, result[0].actions);
        Assertions.assertArrayEquals(this.shortcutsFile, result);
    }
}
