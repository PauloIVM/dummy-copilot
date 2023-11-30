package usecases.keyEventsScanner;

import usecases.interfaces.IKeylistenner;
import entities.clickType.ClickType;
import entities.keyEvent.KeyEvent;
import entities.keyId.KeyId;

import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class KeyEventsScanner {
    private IKeylistenner keylistenner;
    private ArrayList<KeyEvent> keyEventList;
    private KeyId stopKey = KeyId.VK_ENTER;
    private KeyId clearKey = KeyId.VK_ESCAPE;
    private Consumer<ArrayList<KeyEvent>> onKeyListChanged;
    private final BlockingQueue<KeyEvent> keysBlockingQueue;

    public KeyEventsScanner(IKeylistenner k) {
        this.keylistenner = k;
        this.keyEventList = new ArrayList<>();
        this.keysBlockingQueue = new LinkedBlockingQueue<>();
    }

    public KeyEventsScanner setStopKey(KeyId stopKey) {
        this.stopKey = stopKey;
        return this;
    }

    public KeyEventsScanner setClearKey(KeyId clearKey) {
        this.clearKey = clearKey;
        return this;
    }

    public KeyEventsScanner setCallback(Consumer<ArrayList<KeyEvent>> cb) {
        this.onKeyListChanged = cb;
        return this;
    }

    public ArrayList<KeyEvent> next() {
        this.keysBlockingQueue.clear();
        keylistenner.setOnKeyPressedMethod(this::onKeyClicked);
        keylistenner.setOnKeyReleasedMethod(this::onKeyClicked);
        keylistenner.init();
        while (true) {
            try {
                KeyEvent key = keysBlockingQueue.take();
                if (key.keyId.equals(this.stopKey) && key.clickType == ClickType.DOWN) {
                    keylistenner.stop();
                    return this.keyEventList;
                }
                this.keyEventList.add(key);
            } catch (Exception e) {
                keylistenner.stop();
                return this.keyEventList;
            }
        }
    }

    private void onKeyClicked(KeyEvent key) {
        if ((this.keyEventList.size() == 0) && (key.clickType == ClickType.UP)) {
            return;
        }
        if (key.keyId.equals(this.clearKey)) {
            this.keysBlockingQueue.clear();
            this.keyEventList.clear();
            if (this.onKeyListChanged != null) this.onKeyListChanged.accept(null);
            return;
        }
        if (this.onKeyListChanged != null) {
            ArrayList<KeyEvent> currList = (ArrayList) this.keyEventList.clone();
            currList.add(key);
            this.onKeyListChanged.accept(currList);
        }
        try {
            this.keysBlockingQueue.put(key);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
