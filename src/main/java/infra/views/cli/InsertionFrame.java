package infra.views.cli;

import entities.clickType.ClickType;
import entities.keyEvent.KeyEvent;
import entities.shortcut.Shortcut;
import usecases.keyEventsScanner.KeyEventsScanner;
import adapters.keyEventListAdapter.KeyEventListAdapter;
import infra.composers.UsecaseFactory;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.Console;

import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

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

    public Frame run(Scanner scan, Console console) {
        SubFrame currFrame = this.runTriggerSubFrame(console);
        while(true) {
            if (currFrame == SubFrame.INITIAL_FRAME) {
                return Frame.INITIAL_FRAME;
            }
            if (currFrame == SubFrame.NEW_ACTION) {
                currFrame = this.runNewActionSubFrame(scan);
                continue;
            }
            if (currFrame == SubFrame.TRIGGER) {
                currFrame = this.runTriggerSubFrame(console);
                continue;
            }
            if (currFrame == SubFrame.PASTE) {
                currFrame = this.runPasteSubFrame(scan);
                continue;
            }
            if (currFrame == SubFrame.SEQUENCE) {
                currFrame = this.runSequenceSubFrame(scan);
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

    Frame run(Console console) {
        return Frame.INITIAL_FRAME;
    }

    private SubFrame runTriggerSubFrame(Console console) {
        this.shortcut = new Shortcut();
        KeyEventsScanner scanner = UsecaseFactory
            .createKeyEventsScanner()
            .setCallback(this::printKeyEventsTyped);

        String message = "Type the trigger keys (type 'enter' to continue or 'esc' to clear the trigger):";
        this.printKeyEventsInstruction(message);
        AnsiUtil.hideCursor();
        ArrayList<KeyEvent> trigger = scanner.next();
        AnsiUtil.showCursor();
        this.shortcut.setTrigger(trigger);
        return SubFrame.NEW_ACTION;
    }

    private SubFrame runNewActionSubFrame(Scanner scan) {
        AnsiUtil.clear();
        AnsiUtil.setGoldColor();
        System.out.println("DummyCopilot");
        AnsiUtil.setPurpleColor();
        System.out.println("");
        System.out.println("Trigger typed:");
        System.out.println("");
        AnsiUtil.setGoldColor();
        System.out.print("-> ");
        System.out.println(KeyEventListAdapter.toString(this.shortcut.trigger));
        System.out.println("");
        AnsiUtil.setPurpleColor();
        System.out.println("Now choose some actionType or cancel this shortcut:");
        System.out.println("");
        System.out.println("[1] - Paste action.");
        System.out.println("[2] - Sequence action.");
        System.out.println("[3] - Cancel and insert a new shortcut.");
        System.out.println("[4] - Cancel and go to initial menu.");
        System.out.println("");
        AnsiUtil.setGoldColor();
        System.out.print("Enter the number of one of the options above -> ");
        AnsiUtil.setPurpleColor();
        String in = scan.next();
        if (in.equals("1")) return SubFrame.PASTE;
        if (in.equals("2")) return SubFrame.SEQUENCE;
        if (in.equals("3")) return SubFrame.TRIGGER;
        if (in.equals("4")) return SubFrame.INITIAL_FRAME;
        return SubFrame.NEW_ACTION;
    }

    private SubFrame runPasteSubFrame(Scanner scan) {
        AnsiUtil.clear();
        AnsiUtil.setGoldColor();
        System.out.println("DummyCopilot");
        System.out.println("");
        AnsiUtil.setPurpleColor();
        System.out.println("Now insert the content that action should paste:");
        System.out.println("");
        AnsiUtil.setGoldColor();
        System.out.print("-> ");
        AnsiUtil.setPurpleColor();
        String in = scan.next();
        this.shortcut.addAction(in);
        return SubFrame.NEW_ACTION_QUESTION;
    }

    private SubFrame runNewActionQuestionSubFrame(Scanner scan) {
        AnsiUtil.clear();
        AnsiUtil.setGoldColor();
        System.out.println("DummyCopilot");
        System.out.println("");
        AnsiUtil.setPurpleColor();
        System.out.println("Do you want to add one more action?");
        System.out.println("");
        System.out.println("[1] - Yes");
        System.out.println("[2] - No");
        System.out.println("");
        AnsiUtil.setGoldColor();
        System.out.print("-> ");
        AnsiUtil.setPurpleColor();
        String in = scan.next();
        if (in.equals("1")) return SubFrame.NEW_ACTION;
        if (in.equals("2")) return SubFrame.SHORTCUT;
        return SubFrame.NEW_ACTION_QUESTION;
    }

    private SubFrame runNewShortcutQuestionSubFrame(Scanner scan) {
        AnsiUtil.clear();
        AnsiUtil.setGoldColor();
        System.out.println("DummyCopilot");
        System.out.println("");
        AnsiUtil.setPurpleColor();
        System.out.println("Shortcut added. Do you want to add another one?");
        System.out.println("");
        System.out.println("[1] - Yes");
        System.out.println("[2] - No");
        System.out.println("");
        AnsiUtil.setGoldColor();
        System.out.print("-> ");
        AnsiUtil.setPurpleColor();
        String in = scan.next();
        if (in.equals("1")) return SubFrame.TRIGGER;
        if (in.equals("2")) return SubFrame.FINISH;
        return SubFrame.NEW_SHORTCUT_QUESTION;
    }

    private SubFrame runSequenceSubFrame(Scanner scan) {
        KeyEventsScanner scanner = UsecaseFactory
            .createKeyEventsScanner()
            .setCallback(this::printKeyEventsTyped);

        String message = "Type the keys sequence of your action (type 'enter' to continue or 'esc' to clear the trigger):";
        this.printKeyEventsInstruction(message);
        AnsiUtil.hideCursor();
        ArrayList<KeyEvent> keyEventList = scanner.next();
        AnsiUtil.showCursor();
        this.shortcut.addAction(1, keyEventList);
        AnsiUtil.clearTerminalBuffer();
        return SubFrame.NEW_ACTION_QUESTION;
    }

    private SubFrame runShortcutSubFrame(Scanner scan) {
        var shortcutsUpdater = UsecaseFactory.createShortcutUpdater();
        if (shortcutsUpdater.hasTrigger(this.shortcut)) {
            AnsiUtil.clear();
            AnsiUtil.setGoldColor();
            System.out.println("DummyCopilot");
            System.out.println("");
            AnsiUtil.setPurpleColor();
            System.out.println("You are trying to insert a shortcut with a trigger that is already used in another. This will replace the previous shortcut.");
            System.out.println("");
            System.out.println("[1] - Continue");
            System.out.println("[2] - Cancel");
            System.out.println("");
            AnsiUtil.setGoldColor();
            System.out.print("-> ");
            AnsiUtil.setPurpleColor();
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
        AnsiUtil.clear();
        AnsiUtil.setGoldColor();
        System.out.println("DummyCopilot");
        AnsiUtil.setPurpleColor();
        System.out.println("\n");
        System.out.println(mainMessage);
        System.out.println("\n");
        AnsiUtil.setGoldColor();
        System.out.println("-> ");
        System.out.println("\n\n\n");
    }

    private void printKeyEventsTyped(ArrayList<KeyEvent> keyEventList) {
        KeyEvent lastKey = null;
        if (keyEventList != null) lastKey = keyEventList.get(keyEventList.size() - 1);
        if (lastKey != null && lastKey.clickType == ClickType.DOWN) return;
        AnsiUtil.clearCurrLine();
        AnsiUtil.moveLinesUp(5);
        AnsiUtil.clearCurrLine();
        System.out.print("\r-> ");
        if (lastKey == null) {
            System.out.println("\n\n\n\n");
            AnsiUtil.clearCurrLine();
            return;
        }
        System.out.println(KeyEventListAdapter.toString(keyEventList));
        System.out.println("\n\n\n");
        AnsiUtil.clearCurrLine();
    }
}
