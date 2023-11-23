package infra.composers;

import adapters.shortcutsController.ShortcutsController;
import infra.robot.Robot;
import infra.shortcutsDatabase.ShortcutsDatabase;
import infra.keylistenner.Keylistenner;

public class ShortcutsControllerBuilder {
    public ShortcutsControllerBuilder() {}

    public ShortcutsController build() {
        return new ShortcutsController(
            new Robot(),
            new Keylistenner(),
            new ShortcutsDatabase()
        );
    }
}
