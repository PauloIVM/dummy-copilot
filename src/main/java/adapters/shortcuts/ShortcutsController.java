package adapters.shortcuts;

import adapters.interfaces.IKeylistenner;
import adapters.interfaces.IShortcutsFileParser;
import entities.keyEvent.KeyEvent;
import usecases.actions.ActionsExecutor;
import usecases.actions.IRobot;
import usecases.shortcuts.ShortcutsEvaluator;

public class ShortcutsController {
    private IKeylistenner keylistenner;
    private ActionsExecutor actionsExecutor;
    private ShortcutsEvaluator shortcutsEvaluator;

    public ShortcutsController(IRobot r, IKeylistenner k, IShortcutsFileParser s) {
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
