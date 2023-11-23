package infra.composers;

import adapters.shortcutModel.ShortcutModel;
import infra.shortcutsDatabase.ShortcutsDataActionFactory;
import infra.shortcutsDatabase.ShortcutsDataFactory;
import infra.shortcutsDatabase.ShortcutsDatabase;

public class ShortcutModelFactory {
    static public ShortcutModel create() {
        return new ShortcutModel(
            new ShortcutsDatabase(),
            new ShortcutsDataFactory(),
            new ShortcutsDataActionFactory()
        );
    }
}
