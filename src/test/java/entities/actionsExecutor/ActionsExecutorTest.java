package entities.actionsExecutor;

import java.awt.datatransfer.StringSelection;
import java.util.ArrayList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import entities.action.ActionPaste;
import entities.action.ActionSequence;
import entities.actionsExecutor.ActionsExecutor;
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
        Assertions.assertTrue(this.compareStringSelections(new StringSelection("FOO"), robot.pasteOut));
    }

    @Test
    @DisplayName("Should exec sequence action")
    void testSequenceActionExecution() {
        ArrayList<KeyEvent> keysSequenceList = new ArrayList<>();
        keysSequenceList.add(new KeyEvent(KeyId.VK_1, ClickType.DOWN));
        keysSequenceList.add(new KeyEvent(KeyId.VK_1, ClickType.UP));
        int repeat = 2;
        Action action = new ActionSequence(repeat, keysSequenceList);
        Robot robot = new Robot();
        ActionsExecutor actionsExecutor = new ActionsExecutor(robot);
        actionsExecutor.exec(action);
        ArrayList<KeyEvent> expected = new ArrayList<>();
        expected.add(keysSequenceList.get(0));
        expected.add(keysSequenceList.get(1));
        expected.add(keysSequenceList.get(0));
        expected.add(keysSequenceList.get(1));
        Assertions.assertTrue(expected.equals(robot.keysTyped));
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
