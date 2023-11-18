package entities.action;

// TODO: Pesquisar algum pattern interessante pra deixar a Action mais coesa. Estou
// com a sensação q as classes filhas estão soltas demais.
public abstract class Action {
    public final ActionType actionType;

    public Action(ActionType actionType) {
        this.actionType = actionType;
    }
}
