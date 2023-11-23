package adapters.shortcutsController;

import adapters.interfaces.IKeylistenner;
import adapters.interfaces.IShortcutsDatabase;
import entities.keyEvent.KeyEvent;
import usecases.actionsExecutor.ActionsExecutor;
import usecases.actionsExecutor.IRobot;
import usecases.shortcutsEvaluator.ShortcutsEvaluator;

public class ShortcutsController {
    private IKeylistenner keylistenner;
    private ActionsExecutor actionsExecutor;
    private ShortcutsEvaluator shortcutsEvaluator;

    public ShortcutsController(IRobot r, IKeylistenner k, IShortcutsDatabase s) {
        this.actionsExecutor = new ActionsExecutor(r);
        // TODO: Hoje o database está retornando um Shortcut (entidade). Uma modificação
        // interessante seria criar um model apenas pra poder fazer com que o database
        // retorne só o ShortcutData, e não o Shortcut, separando melhor as
        // responsabilidades.
        this.shortcutsEvaluator = new ShortcutsEvaluator(s.get());
        this.keylistenner = k;
    }

    public void init() {
        this.keylistenner
            .setOnKeyPressedMethod(this::onKeyClicked)
            .setOnKeyReleasedMethod(this::onKeyClicked)
            .init();
    }

    public void stop() {
        this.keylistenner.stop();
    }

    private void onKeyClicked(KeyEvent key) {
        this.shortcutsEvaluator.addKeyClicked(key);
        var shortcut = this.shortcutsEvaluator.getShortcutTriggered();
        if (shortcut != null) {
            shortcut.actions.forEach(this.actionsExecutor::exec);
        }
    }
}
