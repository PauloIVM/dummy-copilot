package adapters.interfaces;

public interface IShortcutDataAction {
    public String getType();
    public String getKeys();
    public String getContent();
    public Integer getRepeat();
    public void setType(String type);
    public void setKeys(String keys);
    public void setContent(String content);
    public void setRepeat(Integer repeat);
}
