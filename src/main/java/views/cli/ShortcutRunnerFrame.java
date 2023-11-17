package views.cli;
import java.util.Scanner;
import adapters.shortcuts.ShortcutsController;
import infra.robot.Robot;
import infra.keylistenner.Keylistenner;
import infra.shortcutsfile.ShortcutsFileParser;

import java.io.Console;

public class ShortcutRunnerFrame implements IFrame {
    public Frame run(Scanner scan, Console console) {
        ShortcutsController shortcutsController = new ShortcutsController(
            new Robot(),
            new Keylistenner(),
            new ShortcutsFileParser()
        );
        shortcutsController.init();
        AnsiUtil.clear();
        AnsiUtil.setGoldColor();
        System.out.println("DummyCopilot");
        AnsiUtil.setPurpleColor();
        System.out.println("");
        System.out.println("Running shortcuts-listenner...");
        System.out.println("");
        System.out.println("[1] - Stop shortcuts-listenner.");
        System.out.println("");
        AnsiUtil.setGoldColor();
        System.out.print("Enter the number of one of the options above -> ");
        AnsiUtil.setPurpleColor();
        String in = scan.next();
        shortcutsController.stop();
        if (in.equals("1")) {
            AnsiUtil.clear();
            return Frame.INITIAL_FRAME;
        }
        AnsiUtil.clear();
        return Frame.SHORTCUT_RUNNER_FRAME;
    }
}
