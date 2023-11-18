package usecases.actionsExecutor;

import java.awt.datatransfer.StringSelection;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import entities.action.ActionPaste;
import entities.action.ActionSequence;
import entities.clickType.ClickType;
import entities.keyEvent.KeyEvent;
import entities.keyId.KeyId;
import entities.action.Action;

public class ActionsExecutorTest {
    @Test
    @DisplayName("Should exec paste action")
    void testPasteActionExecution() {
        Action action = new ActionPaste("FOO");
        Robot robot = new Robot();
        ActionsExecutor actionsExecutor = new ActionsExecutor(robot);
        actionsExecutor.exec(action);
        assertTrue(this.compareStringSelections(new StringSelection("FOO"), robot.pasteOut));
    }

    @Test
    @DisplayName("Should exec sequence action")
    void testSequenceActionExecution() {
        ArrayList<KeyEvent> keysSequenceList = new ArrayList<>();
        keysSequenceList.add(new KeyEvent(KeyId.VK_1, ClickType.DOWN));
        keysSequenceList.add(new KeyEvent(KeyId.VK_1, ClickType.UP));
        Action action = new ActionSequence(2, keysSequenceList);
        Robot robot = new Robot();
        ActionsExecutor actionsExecutor = new ActionsExecutor(robot);
        actionsExecutor.exec(action);
        // TODO: Implemetar um equals para o KeyEvent e melhorar esse teste
        // usando o assertArrayEquals
        assertTrue(robot.keysTyped.get(0).keyId == KeyId.VK_1);
        assertTrue(robot.keysTyped.get(0).clickType == ClickType.DOWN);
        assertTrue(robot.keysTyped.get(1).keyId == KeyId.VK_1);
        assertTrue(robot.keysTyped.get(1).clickType == ClickType.UP);
    }

    private Boolean compareStringSelections(StringSelection s1, StringSelection s2) {
        if (s1 == s2) return true;
        if (s1 == null || s2 == null) return false;

        try {
            return s1.getTransferData(s1.getTransferDataFlavors()[0]).equals(
                   s2.getTransferData(s2.getTransferDataFlavors()[0]));
        } catch (Exception e) {
            return false;
        }

    }
}
