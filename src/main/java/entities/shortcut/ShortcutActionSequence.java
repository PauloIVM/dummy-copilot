package entities.shortcut;
import java.util.ArrayList;

public class ShortcutActionSequence extends ShortcutAction {
    public Integer repeat;
    public ArrayList<ShortcutKeyEvent> keysSequence;

    ShortcutActionSequence(Integer repeat, ArrayList<ShortcutKeyEvent> keysSequence) {
        super(ShortcutActionType.SEQUENCE);
        this.repeat = repeat;
        this.keysSequence = keysSequence;
    }
}
