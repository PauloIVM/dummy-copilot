package adapters.interfaces;

public interface IShortcutData {
    public String getTrigger();
    public void setTrigger(String trigger);
    public IShortcutDataAction[] getActions();
    public void setActions(IShortcutDataAction[] actions);
}
