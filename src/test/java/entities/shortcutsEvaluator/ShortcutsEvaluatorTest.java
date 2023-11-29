package entities.shortcutsEvaluator;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import entities.shortcut.Shortcut;
import entities.clickType.ClickType;
import entities.keyId.KeyId;

public class ShortcutsEvaluatorTest {
    ShortcutsEvaluator shortcutsEvaluator;

    @BeforeEach
    void setup() {
        Shortcut shortcut = new Shortcut()
            .addTriggerKey(KeyId.VK_CONTROL, ClickType.DOWN)
            .addTriggerKey(KeyId.VK_T, ClickType.DOWN)
            .addTriggerKey(KeyId.VK_CONTROL, ClickType.UP)
            .addTriggerKey(KeyId.VK_T, ClickType.UP)
            .addTriggerKey(KeyId.VK_G, ClickType.DOWN)
            .addTriggerKey(KeyId.VK_G, ClickType.UP);
        ArrayList<Shortcut> shortcuts = new ArrayList<Shortcut>();
        shortcuts.add(shortcut);
        this.shortcutsEvaluator = new ShortcutsEvaluator(shortcuts);
    }

    @Test
    @DisplayName("Should trigger shortcut")
    void testShortcutTrigger() {
        this.shortcutsEvaluator.addKeyClicked(KeyId.VK_CONTROL, ClickType.DOWN);
        this.shortcutsEvaluator.addKeyClicked(KeyId.VK_T, ClickType.DOWN);
        this.shortcutsEvaluator.addKeyClicked(KeyId.VK_CONTROL, ClickType.UP);
        this.shortcutsEvaluator.addKeyClicked(KeyId.VK_T, ClickType.UP);
        this.shortcutsEvaluator.addKeyClicked(KeyId.VK_G, ClickType.DOWN);
        this.shortcutsEvaluator.addKeyClicked(KeyId.VK_G, ClickType.UP);
        assertEquals(true, this.shortcutsEvaluator.hasShortcutTriggered());
    }

    @Test
    @DisplayName("Should trigger shortcut with previous click")
    void testShortcutTriggerWithPreviousClick() {
        this.shortcutsEvaluator.addKeyClicked(KeyId.VK_P, ClickType.DOWN);
        this.shortcutsEvaluator.addKeyClicked(KeyId.VK_P, ClickType.UP);
        this.shortcutsEvaluator.addKeyClicked(KeyId.VK_CONTROL, ClickType.DOWN);
        this.shortcutsEvaluator.addKeyClicked(KeyId.VK_T, ClickType.DOWN);
        this.shortcutsEvaluator.addKeyClicked(KeyId.VK_CONTROL, ClickType.UP);
        this.shortcutsEvaluator.addKeyClicked(KeyId.VK_T, ClickType.UP);
        this.shortcutsEvaluator.addKeyClicked(KeyId.VK_G, ClickType.DOWN);
        this.shortcutsEvaluator.addKeyClicked(KeyId.VK_G, ClickType.UP);

        this.shortcutsEvaluator.addKeyClicked(KeyId.VK_CONTROL, ClickType.DOWN);
        this.shortcutsEvaluator.addKeyClicked(KeyId.VK_CONTROL, ClickType.UP);
        this.shortcutsEvaluator.addKeyClicked(KeyId.VK_CONTROL, ClickType.DOWN);
        this.shortcutsEvaluator.addKeyClicked(KeyId.VK_T, ClickType.DOWN);
        this.shortcutsEvaluator.addKeyClicked(KeyId.VK_CONTROL, ClickType.UP);
        this.shortcutsEvaluator.addKeyClicked(KeyId.VK_T, ClickType.UP);
        this.shortcutsEvaluator.addKeyClicked(KeyId.VK_G, ClickType.DOWN);
        this.shortcutsEvaluator.addKeyClicked(KeyId.VK_G, ClickType.UP);

        assertEquals(true, this.shortcutsEvaluator.hasShortcutTriggered());
    }

    @Test
    @DisplayName("Should trigger shortcut on rising-edge")
    void testShortcutTriggerRisingEdge() {
        this.shortcutsEvaluator.addKeyClicked(KeyId.VK_CONTROL, ClickType.DOWN);
        this.shortcutsEvaluator.addKeyClicked(KeyId.VK_T, ClickType.DOWN);
        this.shortcutsEvaluator.addKeyClicked(KeyId.VK_T, ClickType.UP);
        this.shortcutsEvaluator.addKeyClicked(KeyId.VK_CONTROL, ClickType.UP);
        this.shortcutsEvaluator.addKeyClicked(KeyId.VK_G, ClickType.DOWN);
        this.shortcutsEvaluator.addKeyClicked(KeyId.VK_G, ClickType.UP);
        assertEquals(true, this.shortcutsEvaluator.hasShortcutTriggered());
    }

    @Test
    @DisplayName("Should not trigger shortcut")
    void testShortcutTriggerWithNotMatch() {
        this.shortcutsEvaluator.addKeyClicked(KeyId.VK_CONTROL, ClickType.DOWN);
        this.shortcutsEvaluator.addKeyClicked(KeyId.VK_T, ClickType.DOWN);
        this.shortcutsEvaluator.addKeyClicked(KeyId.VK_T, ClickType.UP);
        this.shortcutsEvaluator.addKeyClicked(KeyId.VK_CONTROL, ClickType.UP);

        this.shortcutsEvaluator.addKeyClicked(KeyId.VK_CONTROL, ClickType.DOWN);
        this.shortcutsEvaluator.addKeyClicked(KeyId.VK_CONTROL, ClickType.UP);
        this.shortcutsEvaluator.addKeyClicked(KeyId.VK_T, ClickType.DOWN);
        this.shortcutsEvaluator.addKeyClicked(KeyId.VK_T, ClickType.UP);
        this.shortcutsEvaluator.addKeyClicked(KeyId.VK_G, ClickType.DOWN);
        this.shortcutsEvaluator.addKeyClicked(KeyId.VK_G, ClickType.UP);

        assertEquals(false, this.shortcutsEvaluator.hasShortcutTriggered());
    }
}
