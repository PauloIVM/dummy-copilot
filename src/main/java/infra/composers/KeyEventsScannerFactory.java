package infra.composers;

import infra.keylistenner.Keylistenner;
import usecases.keyEventsScanner.KeyEventsScanner;

public class KeyEventsScannerFactory {
    static public KeyEventsScanner create() {
        return new KeyEventsScanner(new Keylistenner());
    }
}
