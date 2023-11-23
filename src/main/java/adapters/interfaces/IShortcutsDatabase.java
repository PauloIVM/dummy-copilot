package adapters.interfaces;

// TODO: Renomear 'ShortcutDataAction' para 'ActionData'??
public interface IShortcutsDatabase {
    public IShortcutData[] get();
    public Boolean update(IShortcutData[] shortcuts);
    public IShortcutData createShortcutData(String trigger, IShortcutDataAction[] actions);
    public IShortcutData[] createShortcutDataArray(Integer size);
    public IShortcutDataAction createShortcutDataAction(String type, String content);
    public IShortcutDataAction createShortcutDataAction(String type, Integer repeat, String keys);
    public IShortcutDataAction[] createShortcutDataActionArray(Integer size);
}
