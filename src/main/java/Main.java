import repositories.ShortcutsFileParser;
import services.shortcuts.ShortcutsManager;
import views.Cli;

import java.awt.Robot;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        // TODO: Essa gambiarra funciona pra esconder os logs da lib, mas nao ficou muito
        // legal. Eu tbm precisaria mover ela pra view do CLI q eu criarei.
        // ShortcutsFileParser shortcutsFileParser = new ShortcutsFileParser();
        // ShortcutsManager shortcutsManager = new ShortcutsManager(
        //     new Robot(),
        //     shortcutsFileParser.get()
        // );
        Cli cli = new Cli();
        cli.runInitialFrame();
    }
}
