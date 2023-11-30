package usecases.interfaces;

import java.util.ArrayList;

import entities.shortcut.Shortcut;

public interface IShortcutRepository {
    public ArrayList<Shortcut> get();
    public Boolean update(ArrayList<Shortcut> shortcuts);
    public Boolean hasTrigger(Shortcut shortcut);
    public Boolean upsert(Shortcut shortcut);
}
