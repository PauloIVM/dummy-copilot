package entities.shortcutsEvaluator;

import java.util.ArrayList;

import entities.clickType.ClickType;
import entities.keyEvent.KeyEvent;
import entities.keyId.KeyId;
import entities.shortcut.Shortcut;

public class ShortcutsEvaluator {
    private ArrayList<KeyEvent> keysClicked;
    private ArrayList<Shortcut> shortcuts;
    private Shortcut triggeredShortcut;

    public ShortcutsEvaluator(ArrayList<Shortcut> shortcuts) {
        this.keysClicked = new ArrayList<>();
        this.shortcuts = shortcuts;
    }

    public Shortcut getShortcutTriggered() {
        return this.triggeredShortcut;
    }

    public void addKeyClicked(KeyId keyId, ClickType clickType) {
        this.addKeyClicked(new KeyEvent(keyId, clickType));
    }

    public void addKeyClicked(KeyEvent keyEvent) {
        this.triggeredShortcut = null;
        this.keysClicked.add(keyEvent);
        Shortcut shortcut = this.shortcuts
            .stream()
            .filter(this::hasPartialTrigger)
            .findFirst()
            .orElse(null);
        if (shortcut == null) {
            this.keysClicked.clear();
            this.keysClicked.add(keyEvent);
            return;
        }
        if (shortcut.trigger.size() == this.keysClicked.size()) {
            this.keysClicked.clear();
            this.triggeredShortcut = shortcut;
        }
    }

    protected Boolean hasShortcutTriggered() {
        return this.triggeredShortcut != null;
    }

    private Boolean hasPartialTrigger(Shortcut shortcut) {
        Boolean[] fallingEdgeMarkers = this.getFallingEdgeMarkers(shortcut.trigger);
        for (int i = 0; i < shortcut.trigger.size(); i++) {
            if (shortcut.trigger.size() <= i || this.keysClicked.size() <= i) {
                continue;
            }
            var triggerKey = shortcut.trigger.get(i);
            var currKeyClicked = this.keysClicked.get(i);
            if (i < fallingEdgeMarkers.length && fallingEdgeMarkers[i]) continue;
            if (this.isSameKey(triggerKey, currKeyClicked) == false) {
                return false;
            }
        }
        return true;
    }

    private Boolean[] getFallingEdgeMarkers(ArrayList<KeyEvent> trigger) {
        Boolean[] fallingEdgeMarkers = new Boolean[trigger.size()];
        for (int i = 0; i < trigger.size(); i++) {
            if (trigger.get(i).clickType == ClickType.DOWN) {
                fallingEdgeMarkers[i] = false;
                continue;
            }
            ClickType UP = ClickType.UP;
            if (i == trigger.size() - 1 && trigger.get(i - 1).clickType == UP) {
                fallingEdgeMarkers[i] = true;
                continue;
            } else if (i == trigger.size() - 1) {
                fallingEdgeMarkers[i] = false;
                continue;

            }
            if (trigger.get(i - 1).clickType == UP || trigger.get(i + 1).clickType == UP) {
                fallingEdgeMarkers[i] = true;
                continue;
            }
            fallingEdgeMarkers[i] = false;

        }
        return fallingEdgeMarkers;
    }

    private Boolean isSameKey(KeyEvent keyA, KeyEvent keyB) {
        if (keyA.keyId.equals(keyB.keyId) && keyA.clickType.equals(keyB.clickType)) {
            return true;
        }
        return false;
    }
}
