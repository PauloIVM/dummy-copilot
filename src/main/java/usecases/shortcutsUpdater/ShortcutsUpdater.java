package usecases.shortcutsUpdater;

import entities.shortcut.Shortcut;
import usecases.interfaces.IShortcutRepository;

import java.util.ArrayList;

public class ShortcutsUpdater {
    private IShortcutRepository shortcutRepository;

    public ShortcutsUpdater(IShortcutRepository s) {
        this.shortcutRepository = s;
    }

    public Boolean hasTrigger(Shortcut shortcut) {
        ArrayList<Shortcut> shortcuts = shortcutRepository.get();
        return shortcuts
            .stream()
            .anyMatch((Shortcut s) -> s.trigger.equals(shortcut.trigger));
    }

    public Boolean upsert(Shortcut shortcut) {
        ArrayList<Shortcut> shortcuts = shortcutRepository.get();
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
        return shortcutRepository.update(shortcuts);
    }
}
