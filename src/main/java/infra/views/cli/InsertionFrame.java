package infra.views.cli;
import java.util.Scanner;
import java.io.Console;

// TODO: Desenvolver esse frame...
public class InsertionFrame implements IFrame {
    public Frame run(Scanner scan, Console console) {
        AnsiUtil.clear();
        AnsiUtil.setGoldColor();
        System.out.println("DummyCopilot");
        AnsiUtil.setPurpleColor();
        System.out.println("");
        System.out.println("Type the trigger keys (to-do: desenvolver):");
        System.out.println("");
        AnsiUtil.setGoldColor();
        System.out.print("-> ");
        AnsiUtil.setPurpleColor();
        String in = scan.next();
        return Frame.INITIAL_FRAME;
    }

    public Frame run(Console console) {
        return Frame.INITIAL_FRAME;
    }
}
