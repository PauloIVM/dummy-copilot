package infra.shortcutsDatabase;

import adapters.interfaces.IShortcutData;
import adapters.interfaces.IShortcutDataAction;
import adapters.interfaces.IShortcutsDataFactory;

public class ShortcutsDataFactory implements IShortcutsDataFactory {
    public IShortcutData createElement(String trigger, IShortcutDataAction[] actions) {
        return new ShortcutData(trigger, (ShortcutDataAction[]) actions);
    }

    public IShortcutData[] createArray(Integer size) {
        return new ShortcutData[size];
    }
}
