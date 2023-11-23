package entities.action;
import java.util.ArrayList;
import entities.keyEvent.KeyEvent;

public class ActionSequence extends Action {
    public Integer repeat;
    public ArrayList<KeyEvent> keysSequence;

    public ActionSequence(Integer repeat, ArrayList<KeyEvent> keysSequence) {
        super(ActionType.SEQUENCE);
        this.repeat = repeat;
        this.keysSequence = keysSequence;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ActionSequence)) return false;
        ActionSequence input = (ActionSequence) obj;
        return input.repeat == this.repeat && input.keysSequence.equals(this.keysSequence);
    }
}
