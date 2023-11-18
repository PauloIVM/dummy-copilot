package infra.composers;

import adapters.shortcutsController.ShortcutsController;
import infra.robot.Robot;
import infra.keylistenner.Keylistenner;
import infra.shortcutsfile.ShortcutsFileParser;

public class ShortcutsControllerBuilder {
    public ShortcutsControllerBuilder() {}

    public ShortcutsController build() {
        return new ShortcutsController(
            new Robot(),
            new Keylistenner(),
            new ShortcutsFileParser()
        );
    }
}
