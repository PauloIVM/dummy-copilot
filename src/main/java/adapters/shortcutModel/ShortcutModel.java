package adapters.shortcutModel;

import java.util.ArrayList;

import adapters.interfaces.IShortcutData;
import adapters.interfaces.IShortcutsDataActionFactory;
import adapters.interfaces.IShortcutsDataFactory;
import adapters.interfaces.IShortcutsDatabase;
import adapters.shortcutDataAdapter.ShortcutsDataAdapter;
import entities.shortcut.Shortcut;

public class ShortcutModel {
    private IShortcutsDatabase db;
    private IShortcutsDataFactory shortcutsDataFactory;
    private IShortcutsDataActionFactory shortcutsDataActionFactory;

    public ShortcutModel(
        IShortcutsDatabase db,
        IShortcutsDataFactory shortcutsDataFactory,
        IShortcutsDataActionFactory shortcutsDataActionFactory
    ) {
        this.db = db;
        this.shortcutsDataFactory = shortcutsDataFactory;
        this.shortcutsDataActionFactory = shortcutsDataActionFactory;
    }

    public ArrayList<Shortcut> get() {
        return ShortcutsDataAdapter.toShortcuts(db.get());
    }

    public Boolean update(ArrayList<Shortcut> shortcuts) {
        IShortcutData[] shortcutsData = ShortcutsDataAdapter.toShortcutsData(
            shortcuts,
            this.shortcutsDataFactory,
            this.shortcutsDataActionFactory
        ); 
        return db.update(shortcutsData);
    }
}
