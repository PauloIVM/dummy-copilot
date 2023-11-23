package infra.composers;

import adapters.shortcutModel.ShortcutModel;
import infra.shortcutsDatabase.ShortcutsDatabase;

public class ShortcutModelFactory {
    static public ShortcutModel create() {
        return new ShortcutModel(new ShortcutsDatabase());
    }
}
