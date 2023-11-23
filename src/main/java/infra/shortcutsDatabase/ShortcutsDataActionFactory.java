package infra.shortcutsDatabase;

import adapters.interfaces.IShortcutDataAction;
import adapters.interfaces.IShortcutsDataActionFactory;

public class ShortcutsDataActionFactory implements IShortcutsDataActionFactory {
    public IShortcutDataAction createElement(String type, String content) {
        return new ShortcutDataAction(type, content);
    }

    public IShortcutDataAction createElement(String type, Integer repeat, String keys) {
        return new ShortcutDataAction(type, repeat, keys);
    }

    public IShortcutDataAction[] createArray(Integer size) {
        return new ShortcutDataAction[size];
    }
}
