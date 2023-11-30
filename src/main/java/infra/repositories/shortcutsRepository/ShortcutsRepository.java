package infra.repositories.shortcutsRepository;

import java.util.ArrayList;

import entities.shortcut.Shortcut;
import usecases.interfaces.IShortcutRepository;

public class ShortcutsRepository implements IShortcutRepository {
    private ShortcutsDatabase db;

    public ShortcutsRepository() {
        this.db = new ShortcutsDatabase();
    }

    // TODO: Criar uma interface pra esse DB pra me permitir criar os testes automatizados
    public ShortcutsRepository(ShortcutsDatabase db) {
        this.db = db;
    }

    public ArrayList<Shortcut> get() {
        return ShortcutsDataAdapter.toShortcuts(db.get());
    }

    public Boolean update(ArrayList<Shortcut> shortcuts) {
        ShortcutData[] shortcutsData = ShortcutsDataAdapter.toShortcutsData(shortcuts); 
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
