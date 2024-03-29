package infra.views.cli;

import entities.clickType.ClickType;
import entities.keyEvent.KeyEvent;
import entities.shortcut.Shortcut;
import adapters.keyEventListAdapter.KeyEventListAdapter;
import infra.composers.UsecaseFactory;

import java.util.ArrayList;
import java.util.Scanner;

enum SubFrame {
    INITIAL_FRAME,
    TRIGGER,
    PASTE,
    SEQUENCE,
    NEW_ACTION,
    NEW_ACTION_QUESTION,
    NEW_SHORTCUT_QUESTION,
    SHORTCUT,
    FINISH
}

class InsertionFrame implements IFrame {
    private Shortcut shortcut;

    public InsertionFrame() {
        this.shortcut = new Shortcut();
    }

    public Frame run(Scanner scan) {
        SubFrame currFrame = this.runTriggerSubFrame();
        while(true) {
            if (currFrame == SubFrame.INITIAL_FRAME) {
                return Frame.INITIAL_FRAME;
            }
            if (currFrame == SubFrame.NEW_ACTION) {
                currFrame = this.runNewActionSubFrame(scan);
                continue;
            }
            if (currFrame == SubFrame.TRIGGER) {
                currFrame = this.runTriggerSubFrame();
                continue;
            }
            if (currFrame == SubFrame.PASTE) {
                currFrame = this.runPasteSubFrame(scan);
                continue;
            }
            if (currFrame == SubFrame.SEQUENCE) {
                currFrame = this.runSequenceSubFrame();
                continue;
            }
            if (currFrame == SubFrame.NEW_ACTION_QUESTION) {
                currFrame = this.runNewActionQuestionSubFrame(scan);
                continue;
            }
            if (currFrame == SubFrame.NEW_SHORTCUT_QUESTION) {
                currFrame = this.runNewShortcutQuestionSubFrame(scan);
                continue;
            }
            if (currFrame == SubFrame.SHORTCUT) {
                currFrame = this.runShortcutSubFrame(scan);
                continue;
            }
            break;
        }
        return Frame.INITIAL_FRAME;
    }

    private SubFrame runTriggerSubFrame() {
        this.shortcut = new Shortcut();
        var scanner = UsecaseFactory
            .createKeyEventsScanner()
            .setCallback(this::printKeyEventsTyped);

        String message = "Type the trigger keys (type 'enter' to continue or 'esc' to clear the trigger):";
        this.printKeyEventsInstruction(message);
        TerminalUtil.hideCursor();
        var trigger = scanner.next();
        TerminalUtil.showCursor();
        this.shortcut.setTrigger(trigger);
        return SubFrame.NEW_ACTION;
    }

    private SubFrame runNewActionSubFrame(Scanner scan) {
        TerminalUtil.clear();
        TerminalUtil.setGoldColor();
        System.out.println("DummyCopilot");
        TerminalUtil.setPurpleColor();
        System.out.println("");
        System.out.println("Trigger typed:");
        System.out.println("");
        TerminalUtil.setGoldColor();
        System.out.print("-> ");
        System.out.println(KeyEventListAdapter.toString(this.shortcut.trigger));
        System.out.println("");
        TerminalUtil.setPurpleColor();
        System.out.println("Now choose some actionType or cancel this shortcut:");
        System.out.println("");
        System.out.println("[1] - Paste action.");
        System.out.println("[2] - Sequence action.");
        System.out.println("[3] - Cancel and insert a new shortcut.");
        System.out.println("[4] - Cancel and go to initial menu.");
        System.out.println("");
        TerminalUtil.setGoldColor();
        System.out.print("Enter the number of one of the options above -> ");
        TerminalUtil.setPurpleColor();
        String in = scan.next();
        if (in.equals("1")) return SubFrame.PASTE;
        if (in.equals("2")) return SubFrame.SEQUENCE;
        if (in.equals("3")) return SubFrame.TRIGGER;
        if (in.equals("4")) return SubFrame.INITIAL_FRAME;
        return SubFrame.NEW_ACTION;
    }

    private SubFrame runPasteSubFrame(Scanner scan) {
        TerminalUtil.clear();
        TerminalUtil.setGoldColor();
        System.out.println("DummyCopilot");
        System.out.println("");
        TerminalUtil.setPurpleColor();
        System.out.println("Now insert the content that action should paste:");
        System.out.println("");
        TerminalUtil.setGoldColor();
        System.out.print("-> ");
        TerminalUtil.setPurpleColor();
        String in = scan.next();
        this.shortcut.addAction(in);
        return SubFrame.NEW_ACTION_QUESTION;
    }

    private SubFrame runNewActionQuestionSubFrame(Scanner scan) {
        TerminalUtil.clear();
        TerminalUtil.setGoldColor();
        System.out.println("DummyCopilot");
        System.out.println("");
        TerminalUtil.setPurpleColor();
        System.out.println("Do you want to add one more action?");
        System.out.println("");
        System.out.println("[1] - Yes");
        System.out.println("[2] - No");
        System.out.println("");
        TerminalUtil.setGoldColor();
        System.out.print("-> ");
        TerminalUtil.setPurpleColor();
        String in = scan.next();
        if (in.equals("1")) return SubFrame.NEW_ACTION;
        if (in.equals("2")) return SubFrame.SHORTCUT;
        return SubFrame.NEW_ACTION_QUESTION;
    }

    private SubFrame runNewShortcutQuestionSubFrame(Scanner scan) {
        TerminalUtil.clear();
        TerminalUtil.setGoldColor();
        System.out.println("DummyCopilot");
        System.out.println("");
        TerminalUtil.setPurpleColor();
        System.out.println("Shortcut added. Do you want to add another one?");
        System.out.println("");
        System.out.println("[1] - Yes");
        System.out.println("[2] - No");
        System.out.println("");
        TerminalUtil.setGoldColor();
        System.out.print("-> ");
        TerminalUtil.setPurpleColor();
        String in = scan.next();
        if (in.equals("1")) return SubFrame.TRIGGER;
        if (in.equals("2")) return SubFrame.FINISH;
        return SubFrame.NEW_SHORTCUT_QUESTION;
    }

    private SubFrame runSequenceSubFrame() {
        var scanner = UsecaseFactory
            .createKeyEventsScanner()
            .setCallback(this::printKeyEventsTyped);

        String message = "Type the keys sequence of your action (type 'enter' to continue or 'esc' to clear the trigger):";
        this.printKeyEventsInstruction(message);
        TerminalUtil.hideCursor();
        var keyEventList = scanner.next();
        TerminalUtil.showCursor();
        this.shortcut.addAction(1, keyEventList);
        TerminalUtil.clearTerminalBuffer();
        return SubFrame.NEW_ACTION_QUESTION;
    }

    private SubFrame runShortcutSubFrame(Scanner scan) {
        var shortcutsUpdater = UsecaseFactory.createShortcutUpdater();
        if (shortcutsUpdater.hasTrigger(this.shortcut)) {
            TerminalUtil.clear();
            TerminalUtil.setGoldColor();
            System.out.println("DummyCopilot");
            System.out.println("");
            TerminalUtil.setPurpleColor();
            System.out.println("You are trying to insert a shortcut with a trigger that is already used in another. This will replace the previous shortcut.");
            System.out.println("");
            System.out.println("[1] - Continue");
            System.out.println("[2] - Cancel");
            System.out.println("");
            TerminalUtil.setGoldColor();
            System.out.print("-> ");
            TerminalUtil.setPurpleColor();
            String in = scan.next();
            if (in.equals("1")) {
                shortcutsUpdater.upsert(shortcut);
                return SubFrame.NEW_SHORTCUT_QUESTION;
            }
            if (in.equals("2")) return SubFrame.INITIAL_FRAME;
            return SubFrame.SHORTCUT;
        }
        shortcutsUpdater.upsert(shortcut);
        return SubFrame.NEW_SHORTCUT_QUESTION;
    }

    private void printKeyEventsInstruction(String mainMessage) {
        TerminalUtil.clear();
        TerminalUtil.setGoldColor();
        System.out.println("DummyCopilot");
        TerminalUtil.setPurpleColor();
        System.out.println("\n");
        System.out.println(mainMessage);
        System.out.println("\n");
        TerminalUtil.setGoldColor();
        System.out.println("-> ");
        System.out.println("\n\n\n");
    }

    private void printKeyEventsTyped(ArrayList<KeyEvent> keyEventList) {
        KeyEvent lastKey = null;
        if (keyEventList != null) lastKey = keyEventList.get(keyEventList.size() - 1);
        if (lastKey != null && lastKey.clickType == ClickType.DOWN) return;
        TerminalUtil.clearCurrLine();
        TerminalUtil.moveLinesUp(5);
        TerminalUtil.clearCurrLine();
        System.out.print("\r-> ");
        if (lastKey == null) {
            System.out.println("\n\n\n\n");
            TerminalUtil.clearCurrLine();
            return;
        }
        System.out.println(KeyEventListAdapter.toString(keyEventList));
        System.out.println("\n\n\n");
        TerminalUtil.clearCurrLine();
    }
}
