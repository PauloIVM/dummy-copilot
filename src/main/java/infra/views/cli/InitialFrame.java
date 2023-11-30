package infra.views.cli;

import java.util.Scanner;

class InitialFrame implements IFrame {
    public Frame run(Scanner scan) {
        TerminalUtil.clear();
        TerminalUtil.showCursor();
        TerminalUtil.setGoldColor();
        System.out.println("DummyCopilot");
        TerminalUtil.setPurpleColor();
        System.out.println("");
        System.out.println("[1] - Enter a new shortcut.");
        System.out.println("[2] - Start shortcuts-listenner.");
        System.out.println("[3] - Start keylogger.");
        System.out.println("[4] - Exit.");
        System.out.println("");
        TerminalUtil.setGoldColor();
        System.out.print("Enter the number of one of the options above -> ");
        TerminalUtil.setPurpleColor();
        String in = scan.next();
        if (in.equals("1")) {
            return Frame.INSERTION_FRAME;
        }
        if (in.equals("2")) {
            return Frame.SHORTCUT_RUNNER_FRAME;
        }
        if (in.equals("3")) {
            return Frame.KEYLOGGER_FRAME;
        }
        if (in.equals("4")) {
            System.runFinalization();
            System.exit(0);
        }
        return Frame.INITIAL_FRAME;
    }
}
