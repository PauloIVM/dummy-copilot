package infra.shortcutsfile;

public class ShortcutFileAction {
    public String type;
    public String keys;   
    public String content;   
    public Integer repeat;  

    ShortcutFileAction(String type, String content) {
        this.type = type;
        this.content = content;
    }

    ShortcutFileAction(String type, Integer repeat, String keys) {
        this.type = type;
        this.keys = keys;
        this.repeat = repeat;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ShortcutFileAction)) return false;
        ShortcutFileAction o = (ShortcutFileAction) obj;
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
