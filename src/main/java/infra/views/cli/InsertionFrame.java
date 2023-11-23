package infra.views.cli;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Consumer;

import adapters.keyEventListAdapter.KeyEventListAdapter;
import entities.keyEvent.KeyEvent;
import entities.keyId.KeyId;
import entities.shortcut.Shortcut;

import java.io.Console;

import infra.keylistenner.Keylistenner;
import infra.shortcutsDatabase.ShortcutsDatabase;

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
    private ShortcutsDatabase shortcutsDatabase;

    public InsertionFrame() {
        this.shortcut = new Shortcut();
        this.shortcutsDatabase = new ShortcutsDatabase();
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
                currFrame = this.runSequenceSubFrame(console);
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
        Consumer<KeyEvent> onKeyClicked = (KeyEvent key) -> {
            if (key.keyId == KeyId.VK_ENTER) {
                System.out.print(String.format("\033[%dA", 1));
                System.out.print("\033[2K");
                System.out.print("-> ");
                System.out.println("You typed 'enter' with another application focused. Please click on the terminal to type 'enter' key and continue.");
                return;
            }
            this.shortcut.addTriggerKey(key.keyId, key.clickType);
        };
        Consumer<KeyEvent> onKeyReleased = (KeyEvent key) -> {
            if (key.keyId == KeyId.VK_ENTER) return;
            // TODO: Deveria existir um que eu passasse só a "key" nos params.
            this.shortcut.addTriggerKey(key.keyId, key.clickType);
            if (key.keyId == KeyId.VK_ESCAPE) this.shortcut.clearTrigger();
            System.out.print(String.format("\033[%dA", 1));
            System.out.print("\033[2K");
            System.out.print("-> ");
            System.out.println(KeyEventListAdapter.toString(this.shortcut.trigger));
        };
        Keylistenner keylistenner = new Keylistenner();
        keylistenner.setOnKeyPressedMethod(onKeyClicked);
        keylistenner.setOnKeyReleasedMethod(onKeyReleased);
        keylistenner.init();
        AnsiUtil.clear();
        AnsiUtil.setGoldColor();
        System.out.println("DummyCopilot");
        AnsiUtil.setPurpleColor();
        System.out.println("");
        System.out.println("Type the trigger keys (type 'enter' to continue or 'esc' to clear the trigger):");
        System.out.println("");
        AnsiUtil.setGoldColor();
        AnsiUtil.hideCursor();
        System.out.println("-> ");
        console.readPassword("");
        keylistenner.stop();
        AnsiUtil.showCursor();
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
        AnsiUtil.showCursor();
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
        AnsiUtil.showCursor();
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
        AnsiUtil.showCursor();
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

    private SubFrame runSequenceSubFrame(Console console) {
        ArrayList<KeyEvent> keyEventList = new ArrayList<>();
        Consumer<KeyEvent> onKeyClicked = (KeyEvent key) -> {
            if (key.keyId == KeyId.VK_ENTER) {
                System.out.print(String.format("\033[%dA", 1));
                System.out.print("\033[2K");
                System.out.print("-> ");
                System.out.println("You typed 'enter' with another application focused. Please click on the terminal to type 'enter' key and continue.");
                return;
            }
            keyEventList.add(new KeyEvent(key.keyId, key.clickType));
        };
        Consumer<KeyEvent> onKeyReleased = (KeyEvent key) -> {
            if (key.keyId == KeyId.VK_ENTER) return;
            keyEventList.add(new KeyEvent(key.keyId, key.clickType));
            if (key.keyId == KeyId.VK_ESCAPE) keyEventList.clear();
            System.out.print(String.format("\033[%dA", 1));
            System.out.print("\033[2K");
            System.out.print("-> ");
            System.out.println(KeyEventListAdapter.toString(keyEventList));
        };
        Keylistenner keylistenner = new Keylistenner();
        keylistenner.setOnKeyPressedMethod(onKeyClicked);
        keylistenner.setOnKeyReleasedMethod(onKeyReleased);
        keylistenner.init();
        AnsiUtil.clear();
        AnsiUtil.setGoldColor();
        System.out.println("DummyCopilot");
        AnsiUtil.setPurpleColor();
        System.out.println("");
        System.out.println("Type the keys sequence of your action (type 'enter' to continue or 'esc' to clear the trigger):");
        System.out.println("");
        AnsiUtil.setGoldColor();
        AnsiUtil.hideCursor();
        System.out.println("-> ");
        console.readPassword("");
        keylistenner.stop();
        AnsiUtil.showCursor();
        // TODO: Adicionar pergunta para o número de repetições..
        this.shortcut.addAction(1, keyEventList);
        return SubFrame.NEW_ACTION_QUESTION;
    }

    private SubFrame runShortcutSubFrame(Scanner scan) {
        ArrayList<Shortcut> shortcuts = this.shortcutsDatabase.get();
        Shortcut shortcutWithSameTrigger = shortcuts
            .stream()
            .filter((Shortcut s) -> s.trigger.equals(this.shortcut.trigger))
            .findFirst()
            .orElse(null);

        if (shortcutWithSameTrigger != null) {
            AnsiUtil.clear();
            AnsiUtil.showCursor();
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
                Integer index = shortcuts.indexOf(shortcutWithSameTrigger);
                shortcuts.set(index, this.shortcut);
                this.shortcutsDatabase.update(shortcuts);
                return SubFrame.NEW_SHORTCUT_QUESTION;
            }
            if (in.equals("2")) return SubFrame.INITIAL_FRAME;
            return SubFrame.SHORTCUT;
        }
        shortcuts.add(this.shortcut);
        this.shortcutsDatabase.update(shortcuts);
        return SubFrame.NEW_SHORTCUT_QUESTION;
    }
}
