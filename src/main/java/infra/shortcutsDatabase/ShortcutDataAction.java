package infra.shortcutsDatabase;

import adapters.interfaces.IShortcutDataAction;

public class ShortcutDataAction implements IShortcutDataAction  {
    public String type;
    public String keys;
    public String content;   
    public Integer repeat;

    public ShortcutDataAction(String type, String content) {
        this.type = type;
        this.content = content;
    }

    public ShortcutDataAction(String type, Integer repeat, String keys) {
        this.type = type;
        this.keys = keys;
        this.repeat = repeat;
    }

    public String getType() { return this.type; }
    public String getKeys() { return this.keys; }
    public String getContent() { return this.content; }
    public Integer getRepeat() { return this.repeat; }
    public void setType(String type) { this.type = type; }
    public void setKeys(String keys) { this.keys = keys; }
    public void setContent(String content) { this.content = content; }
    public void setRepeat(Integer repeat) { this.repeat = repeat; }
    
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ShortcutDataAction)) return false;
        ShortcutDataAction o = (ShortcutDataAction) obj;
        if (o.type != null && o.content != null) {
            return o.type.equals(this.type) && o.content.equals(this.content);
        }
        if (o.type == null || o.keys == null || o.repeat == null) return false;
        return (
            o.type.equals(this.type) &&
            o.keys.equals(this.keys) &&
            o.repeat.equals(this.repeat)
        );
    }
}
