package infra.composers;

import infra.keylistenner.Keylistenner;
import infra.repositories.shortcutsRepository.ShortcutsRepository;
import infra.robot.Robot;
import usecases.keyEventsScanner.KeyEventsScanner;
import usecases.keyScanner.KeyScanner;
import usecases.shortcutsRunner.ShortcutsRunner;
import usecases.shortcutsUpdater.ShortcutsUpdater;

public class UsecaseFactory {

    static public ShortcutsRunner createShortcutsRunner() {
        return new ShortcutsRunner(
            new Robot(),
            new Keylistenner(),
            new ShortcutsRepository()
        );
    }

    static public ShortcutsUpdater createShortcutUpdater() {
        return new ShortcutsUpdater(new ShortcutsRepository());
    }

    static public KeyEventsScanner createKeyEventsScanner() {
        return new KeyEventsScanner(new Keylistenner());
    }

    static public KeyScanner createKeyScanner() {
        return new KeyScanner(new Keylistenner());
    }
}
