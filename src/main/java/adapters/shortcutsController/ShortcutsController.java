package adapters.shortcutsController;

import usecases.interfaces.IKeylistenner;
import adapters.shortcutModel.ShortcutModel;
import entities.actionsExecutor.ActionsExecutor;
import entities.actionsExecutor.IRobot;
import entities.keyEvent.KeyEvent;
import entities.shortcutsEvaluator.ShortcutsEvaluator;

public class ShortcutsController {
    private IKeylistenner keylistenner;
    private ActionsExecutor actionsExecutor;
    private ShortcutsEvaluator shortcutsEvaluator;

    public ShortcutsController(IRobot r, IKeylistenner k, ShortcutModel s) {
        this.actionsExecutor = new ActionsExecutor(r);
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
