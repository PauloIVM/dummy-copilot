package infra.views.cli;

import infra.composers.UsecaseFactory;

import java.util.Scanner;

class ShortcutRunnerFrame implements IFrame {
    public Frame run(Scanner scan) {
        var shortcutsRunner = UsecaseFactory.createShortcutsRunner();
        shortcutsRunner.init();
        TerminalUtil.clear();
        TerminalUtil.setGoldColor();
        System.out.println("DummyCopilot");
        TerminalUtil.setPurpleColor();
        System.out.println("");
        System.out.println("Running shortcuts-listenner...");
        System.out.println("");
        System.out.println("[1] - Stop shortcuts-listenner.");
        System.out.println("");
        TerminalUtil.setGoldColor();
        System.out.print("Enter the number of one of the options above -> ");
        TerminalUtil.setPurpleColor();
        String in = scan.next();
        shortcutsRunner.stop();
        if (in.equals("1")) {
            TerminalUtil.clear();
            return Frame.INITIAL_FRAME;
        }
        TerminalUtil.clear();
        return Frame.SHORTCUT_RUNNER_FRAME;
    }
}
