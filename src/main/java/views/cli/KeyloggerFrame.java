package views.cli;
import adapters.keylogger.Keylogger;
import infra.keylistenner.Keylistenner;
import java.util.Scanner;
import java.io.Console;
import java.util.function.Consumer;

public class KeyloggerFrame implements IFrame {
    public Frame run(Scanner scan, Console console) {
        // TODO Criar composers para facilitar instanciação desses caras:
        Consumer<String> onKeyPressed = (String key) -> {
            System.out.print(String.format("\033[%dA", 1));
            System.out.print("\033[2K");
            System.out.print("-> ");
            System.out.println(key);
        };
        Keylistenner keylistenner = new Keylistenner();
        Keylogger keylogger = new Keylogger()
            .setConsumer(onKeyPressed)
            .setKeylistenner(keylistenner);
        keylogger.init();
        AnsiUtil.clear();
        AnsiUtil.setGoldColor();
        System.out.println("DummyCopilot");
        AnsiUtil.setPurpleColor();
        System.out.println("");
        System.out.println("Type any key to see the keycode on terminal. Type 'enter' to exit.");
        System.out.println("");
        AnsiUtil.setGoldColor();
        AnsiUtil.hideCursor();
        System.out.println("-> ");
        console.readPassword("");
        keylogger.stop();
        AnsiUtil.showCursor();
        AnsiUtil.clear();
        return Frame.INITIAL_FRAME;
    }
}
