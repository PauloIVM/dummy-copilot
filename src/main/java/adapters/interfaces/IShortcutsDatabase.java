package adapters.interfaces;

public interface IShortcutsDatabase {
    public IShortcutData[] get();
    public Boolean update(IShortcutData[] shortcuts);
}
