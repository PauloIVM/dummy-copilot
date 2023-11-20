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
    }

    @Test
    @DisplayName("Should create shortcuts")
    void testBasicShortcutsCreation() {
        ArrayList<Shortcut> result = this.shortcutsFileParser.createShortcuts(this.shortcutsFile);
        ArrayList<KeyEvent> keyEventList = new ArrayList<>();
        keyEventList.add(new KeyEvent(KeyId.VK_A, ClickType.DOWN));
        keyEventList.add(new KeyEvent(KeyId.VK_A, ClickType.UP));
        keyEventList.add(new KeyEvent(KeyId.VK_B, ClickType.DOWN));
        keyEventList.add(new KeyEvent(KeyId.VK_B, ClickType.UP));
        ArrayList<Shortcut> expected = new ArrayList<>();
        expected.add(
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
        expected.add(
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
        Assertions.assertTrue(expected.equals(result));
    }
}
