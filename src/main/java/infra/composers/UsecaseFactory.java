package infra.composers;

import infra.keylistenner.Keylistenner;
import infra.repositories.shortcutsRepository.ShortcutsRepository;
import infra.robot.Robot;
import usecases.keyEventsScanner.KeyEventsScanner;
import usecases.shortcutUpdater.ShortcutUpdater;
import usecases.shortcutsRunner.ShortcutsRunner;

public class UsecaseFactory {
    static public KeyEventsScanner createKeyEventsScanner() {
        return new KeyEventsScanner(new Keylistenner());
    }

    static public ShortcutsRunner createShortcutsRunner() {
        return new ShortcutsRunner(
            new Robot(),
            new Keylistenner(),
            new ShortcutsRepository()
        );
    }

    static public ShortcutUpdater createShortcutUpdater() {
        return new ShortcutUpdater(new ShortcutsRepository());
    } 
}
