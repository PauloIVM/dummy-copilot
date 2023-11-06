import repositories.ShortcutsFileParser;
import services.shortcuts.ShortcutsManager;

import java.awt.Robot;

public class Main {
    public static void main(String[] args) throws Exception {
        ShortcutsFileParser shortcutsFileParser = new ShortcutsFileParser();
        ShortcutsManager shortcutsManager = new ShortcutsManager(
            new Robot(),
            shortcutsFileParser.get()
        );
    }
}
