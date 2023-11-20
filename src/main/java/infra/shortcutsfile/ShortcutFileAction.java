package infra.shortcutsfile;

public class ShortcutFileAction {
    ShortcutFileAction(String type, String content) {
        this.type = type;
        this.content = content;
    }

    ShortcutFileAction(String type, Integer repeat, String keys) {
        this.type = type;
        this.keys = keys;
        this.repeat = repeat;
    }
    public String type;
    public String keys;   
    public String content;   
    public Integer repeat;   
}
