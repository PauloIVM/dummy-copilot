package views.cli;
import java.util.Scanner;
import repositories.ShortcutsFileParser;
import services.shortcuts.ShortcutsRobot;
import services.shortcuts.ShortcutsManager;
import java.io.Console;

public class ShortcutRunnerFrame implements IFrame {
    public Frame run(Scanner scan, Console console) {
        ShortcutsFileParser shortcutsFileParser = new ShortcutsFileParser();
        ShortcutsManager shortcutsManager = null;
        try {
            // TODO: Posso criar uma factory q já monta isso pra mim, injeta as dependencias,
            //  e já faz o tratamento da exception. Inserir o InjectorFactory no código.
            shortcutsManager = new ShortcutsManager(
                new ShortcutsRobot(),
                shortcutsFileParser.get()
            );
        } catch (Exception e) {}
        if (shortcutsManager != null) {
            shortcutsManager.init();
        }
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
        if (shortcutsManager != null) {
            shortcutsManager.stop();
        }
        if (in.equals("1")) {
            AnsiUtil.clear();
            return Frame.INITIAL_FRAME;
        }
        AnsiUtil.clear();
        return Frame.SHORTCUT_RUNNER_FRAME;
    }
}
