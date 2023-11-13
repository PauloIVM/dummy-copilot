package views.cli;
import services.keylogger.ILogger;

public class Logger implements ILogger {
    public void run(String key) {
        System.out.print(String.format("\033[%dA", 1));
        System.out.print("\033[2K");
        System.out.print("-> ");
        System.out.println(key);
    }
}
