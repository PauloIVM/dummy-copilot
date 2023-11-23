package adapters.shortcutModel;

import java.util.ArrayList;

import adapters.interfaces.IShortcutData;
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
        IShortcutData[] shortcutsData = ShortcutsDataAdapter.toShortcutsData(
            shortcuts,
            db
        ); 
        return db.update(shortcutsData);
    }

    public Boolean hasTrigger(Shortcut shortcut) {
        ArrayList<Shortcut> shortcuts = this.get();
        return shortcuts
            .stream()
            .anyMatch((Shortcut s) -> s.trigger.equals(shortcut.trigger));
    }

    public Boolean upsert(Shortcut shortcut) {
        ArrayList<Shortcut> shortcuts = this.get();
        Shortcut shortcutWithSameTrigger = shortcuts
            .stream()
            .filter((Shortcut s) -> s.trigger.equals(shortcut.trigger))
            .findFirst()
            .orElse(null);
        if (shortcutWithSameTrigger != null) {
            Integer index = shortcuts.indexOf(shortcutWithSameTrigger);
            shortcuts.set(index, shortcut);
        } else {
            shortcuts.add(shortcut);
        }
        return this.update(shortcuts);
    }
}
