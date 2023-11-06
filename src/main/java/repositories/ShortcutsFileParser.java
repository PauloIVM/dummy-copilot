package repositories;
import java.util.ArrayList;
import entities.shortcut.Shortcut;
import entities.shortcut.ShortcutClickType;
import entities.shortcut.ShortcutKeyEvent;
import entities.shortcut.ShortcutKeyId;

// TODO: Implementar a leitura de um json e baseado nisso a geração dos "Shortcut".

public class ShortcutsFileParser {
    public ShortcutsFileParser() {}

    public ArrayList<Shortcut> get() {
        // TODO: Usar a nomeclatura igual a do vscode (ctrl+t g p) no json, e parsear para
        // isso daqui...
        Shortcut shortcut = new Shortcut()
            .addTriggerKey(ShortcutKeyId.VK_CONTROL, ShortcutClickType.DOWN)
            .addTriggerKey(ShortcutKeyId.VK_T, ShortcutClickType.DOWN)
            .addTriggerKey(ShortcutKeyId.VK_CONTROL, ShortcutClickType.UP)
            .addTriggerKey(ShortcutKeyId.VK_T, ShortcutClickType.UP)
            .addTriggerKey(ShortcutKeyId.VK_G, ShortcutClickType.DOWN)
            .addTriggerKey(ShortcutKeyId.VK_G, ShortcutClickType.UP)
            .addTriggerKey(ShortcutKeyId.VK_P, ShortcutClickType.DOWN)
            .addTriggerKey(ShortcutKeyId.VK_P, ShortcutClickType.UP)
            .addAction(
                2,
                new ShortcutKeyEvent(ShortcutKeyId.VK_BACK_SPACE, ShortcutClickType.DOWN),
                new ShortcutKeyEvent(ShortcutKeyId.VK_BACK_SPACE, ShortcutClickType.UP)
            )
            .addAction("git push --set-upstream origin $(git rev-parse --abbrev-ref HEAD)");
        ArrayList<Shortcut> shortcuts = new ArrayList<Shortcut>();
        shortcuts.add(shortcut);
        return shortcuts;
    }
}
