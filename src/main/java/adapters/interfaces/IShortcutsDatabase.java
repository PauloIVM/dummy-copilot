package adapters.interfaces;
import java.util.ArrayList;

import entities.shortcut.Shortcut;

public interface IShortcutsDatabase {
    public ArrayList<Shortcut> get();
}
