package adapters.interfaces;

public interface IShortcutsDataFactory {
    public IShortcutData createElement(String trigger, IShortcutDataAction[] actions);
    public IShortcutData[] createArray(Integer size);
}
