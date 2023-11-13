package views.cli;
import services.keylogger.ILogger;
import services.keylogger.Keylogger;
import java.util.Scanner;
import java.io.Console;

public class KeyloggerFrame implements IFrame {
    public Frame run(Scanner scan, Console console) {
        Keylogger keylogger = null;
        ILogger logger = new Logger();
        try { keylogger = new Keylogger(logger); } catch (Exception e) {}
        if (keylogger != null) { keylogger.initListenner(); }
        if (keylogger == null) {
            AnsiUtil.clear();
            return Frame.INITIAL_FRAME;    
        }
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
        if (keylogger != null) { keylogger.finishListenner(); }
        AnsiUtil.showCursor();
        AnsiUtil.clear();
        return Frame.INITIAL_FRAME;
    }
}
