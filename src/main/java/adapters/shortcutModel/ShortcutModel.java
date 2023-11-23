package adapters.shortcutModel;

import java.util.ArrayList;

import adapters.interfaces.IShortcutsDatabase;
import adapters.shortcutDataAdapter.ShortcutsDataAdapter;
import entities.shortcut.Shortcut;

public class ShortcutModel {
    private IShortcutsDatabase db;

    public ShortcutModel(IShortcutsDatabase db) {
        this.db = db;
    }

    public ArrayList<Shortcut> get() {
        return ShortcutsDataAdapter.toShortcuts(db.get());
    }

    public Boolean update(ArrayList<Shortcut> shortcuts) {
        return db.update(ShortcutsDataAdapter.toShortcutsData(shortcuts));
    }
}
