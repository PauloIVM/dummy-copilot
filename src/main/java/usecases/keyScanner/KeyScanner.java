package usecases.keyScanner;

import java.io.Closeable;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import entities.clickType.ClickType;
import entities.keyEvent.KeyEvent;
import usecases.interfaces.IKeylistenner;

public class KeyScanner implements Closeable {
    private IKeylistenner keylistenner;
    private ArrayList<KeyEvent> keyEventList;
    private final BlockingQueue<KeyEvent> keysBlockingQueue;

    public KeyScanner(IKeylistenner k) {
        this.keylistenner = k;
        this.keyEventList = new ArrayList<>();
        this.keysBlockingQueue = new LinkedBlockingQueue<>();
        this.keylistenner.setOnKeyPressedMethod(this::onKeyClicked);
        this.keylistenner.setOnKeyReleasedMethod(this::onKeyClicked);
        this.keylistenner.init();
    }

    public KeyEvent next() {
        try {
            KeyEvent key = keysBlockingQueue.take();
            return key;
        } catch (Exception e) {
            return null;
        }
    }

    public void close() {
        keylistenner.stop();
    }

    private void onKeyClicked(KeyEvent key) {
        if ((this.keyEventList.size() == 0) && (key.clickType == ClickType.UP)) {
            return;
        }
        try {
            this.keysBlockingQueue.put(key);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
