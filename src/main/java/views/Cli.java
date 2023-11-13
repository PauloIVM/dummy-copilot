package views;

import java.util.Scanner;
import repositories.ShortcutsFileParser;
import services.shortcuts.ShortcutsManager;
import services.keylogger.Keylogger;
import java.awt.Robot;
import java.io.Console;

// TODO: Tem uns leaks de memória aqui, principalmente pq eu não estou fechando os scans,
// e acredito q tbm por conta das recursões.

public class Cli {
    public Cli() {}

    public void runInitialFrame() {
        this.clear();
        this.showCursor();
        this.setGoldColor();
        System.out.println("DummyCopilot");
        this.setPurpleColor();
        System.out.println("");
        System.out.println("[1] - Enter a new shortcut.");
        System.out.println("[2] - Start shortcuts-listenner.");
        System.out.println("[3] - Start keylogger.");
        System.out.println("[4] - Exit.");
        System.out.println("");
        this.setGoldColor();
        System.out.print("Enter the number of one of the options above -> ");
        this.setPurpleColor();
        Scanner scan = new Scanner(System.in);
        String in = scan.next();
        if (in.equals("1")) {
            this.runShortcutInsertionFrame();
            scan.close();
            return;
        }
        if (in.equals("2")) {
            this.runShortcutRunnerFrame();
            scan.close();
            return;
        }
        if (in.equals("3")) {
            this.runKeyloggerFrame();
            scan.close();
            return;
        }
        if (in.equals("4")) {
            System.runFinalization();
            scan.close();
            System.exit(0);
            return;
        }
        scan.close();
        this.runInitialFrame();
    }

    public void runShortcutInsertionFrame() {
        this.clear();
        this.setGoldColor();
        System.out.println("DummyCopilot");
        this.setPurpleColor();
        System.out.println("");
        System.out.println("Type the trigger keys (to-do: desenvolver):");
        System.out.println("");
        this.setGoldColor();
        System.out.print("-> ");
        this.setPurpleColor();
        Scanner scan = new Scanner(System.in);
        String in = scan.next();
        scan.close();
    }

    public void runKeyloggerFrame() {
        // TODO: Isso pode gerar um leak tbm???
        Keylogger keylogger = null;
        try { keylogger = new Keylogger(); } catch (Exception e) {}
        if (keylogger != null) { keylogger.initListenner(); }
        if (keylogger == null) {
            this.clear();
            this.runInitialFrame();    
        }
        this.clear();
        this.setGoldColor();
        System.out.println("DummyCopilot");
        this.setPurpleColor();
        System.out.println("");
        System.out.println("Type any key to see the keycode on terminal. Type 'enter' to exit.");
        System.out.println("");
        this.setGoldColor();
        this.hideCursor();
        Console console = System.console();
        // TODO: Melhorar...
        System.out.println("-> ");
        console.readPassword("");
        if (keylogger != null) { keylogger.finishListenner(); }
        this.showCursor();
        this.clear();
        this.runInitialFrame();
    }

    public void runShortcutRunnerFrame() {
        ShortcutsFileParser shortcutsFileParser = new ShortcutsFileParser();
        // TODO: Isso pode gerar um leak tbm???
        ShortcutsManager shortcutsManager = null;
        try {
            shortcutsManager = new ShortcutsManager(
                new Robot(),
                shortcutsFileParser.get()
            );
        } catch (Exception e) {}
        if (shortcutsManager != null) {
            shortcutsManager.initListenner();
        }
        this.clear();
        this.setGoldColor();
        System.out.println("DummyCopilot");
        this.setPurpleColor();
        System.out.println("");
        System.out.println("Running shortcuts-listenner...");
        System.out.println("");
        System.out.println("[1] - Stop shortcuts-listenner.");
        System.out.println("");
        this.setGoldColor();
        System.out.print("Enter the number of one of the options above -> ");
        this.setPurpleColor();
        Scanner scan = new Scanner(System.in);
        String in = scan.next();
        if (shortcutsManager != null) {
            shortcutsManager.finishListenner();
        }
        if (in.equals("1")) {
            // scan.close();
            this.clear();
            this.runInitialFrame();
        }
        // scan.close();
        this.clear();
        this.runShortcutRunnerFrame();
    }

    private void clear() {
        System.out.print("\033[H\033[2J");
    }

    private void setGoldColor() {
        System.out.print("\033[38;5;220m");
    }

    private void setGreenColor() {
        System.out.print("\033[38;5;2m");
    }

    private void setPurpleColor() {
        System.out.print("\033[38;5;213m");
    }

    private void hideCursor() {
        System.out.print("\033[?25l");
    }

    private void showCursor() {
        System.out.print("\033[?25h");
    }
}
