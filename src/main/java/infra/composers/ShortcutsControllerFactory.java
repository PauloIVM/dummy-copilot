package infra.composers;

import adapters.shortcutModel.ShortcutModel;
import adapters.shortcutsController.ShortcutsController;
import infra.robot.Robot;
import infra.shortcutsDatabase.ShortcutsDatabase;
import infra.keylistenner.Keylistenner;

public class ShortcutsControllerFactory {
    static public ShortcutsController create() {
        return new ShortcutsController(
            new Robot(),
            new Keylistenner(),
            new ShortcutModel(new ShortcutsDatabase())
        );
    }
}
