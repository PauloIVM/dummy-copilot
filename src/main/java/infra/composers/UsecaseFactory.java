package infra.composers;

import infra.keylistenner.Keylistenner;
import usecases.keyEventsScanner.KeyEventsScanner;

public class UsecaseFactory {
    static public KeyEventsScanner createKeyEventsScanner() {
        return new KeyEventsScanner(new Keylistenner());
    }
}
