package infra.views.cli;
import java.util.Scanner;

public class Cli {
    public Cli() {}

    public void run() {
        IFrame frame = new InitialFrame();
        Frame nextFrame = Frame.INITIAL_FRAME;
        Scanner scan = new Scanner(System.in);
        while(true) {
            nextFrame = frame.run(scan);
            if (nextFrame == Frame.INITIAL_FRAME) {
                frame = new InitialFrame();
            }
            if (nextFrame == Frame.INSERTION_FRAME) {
                frame = new InsertionFrame();
            }
            if (nextFrame == Frame.KEYLOGGER_FRAME) {
                frame = new KeyloggerFrame();
            }
            if (nextFrame == Frame.SHORTCUT_RUNNER_FRAME) {
                frame = new ShortcutRunnerFrame();
            }
        }
    }
}
