package adapters.interfaces;

public interface IShortcutsDataActionFactory {
    public IShortcutDataAction createElement(String type, String content);
    public IShortcutDataAction createElement(String type, Integer repeat, String keys);
    public IShortcutDataAction[] createArray(Integer size);
}
