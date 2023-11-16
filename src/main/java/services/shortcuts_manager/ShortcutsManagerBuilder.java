package services.shortcuts_manager;
import java.util.ArrayList;

import entities.shortcut.Shortcut;
import repositories.ShortcutsFileParser;

public class ShortcutsManagerBuilder {
    private IRobot robot;
    private ArrayList<Shortcut> shortcuts;

    public ShortcutsManagerBuilder() {}

    public ShortcutsManagerBuilder buildRobot() {
        try {
            this.robot = new ShortcutsRobot();
        } catch (Exception e) {
            System.err.println("There was a problem to setup robot");
            System.err.println(e.getMessage());
            System.exit(1);
        }
        return this;
    }

    public ShortcutsManagerBuilder loadShortcuts() {
        ShortcutsFileParser shortcutsFileParser = new ShortcutsFileParser();
        this.shortcuts = shortcutsFileParser.get();
        return this;
    }

    public ShortcutsManager build() {
        return new ShortcutsManager(this.robot, this.shortcuts);
    }
}
