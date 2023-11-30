package infra.views.cli;

import java.util.Scanner;
import java.io.Console;
import java.util.function.Consumer;

import infra.composers.UsecaseFactory;

class KeyloggerFrame implements IFrame {
    public Frame run(Scanner scan, Console console) {
        Consumer<String> onKeyPressed = (String key) -> {
            if (key == "enter") {
                System.out.print(String.format("\033[%dA", 1));
                System.out.print("\033[2K");
                System.out.print("-> ");
                System.out.println("You typed 'enter' with another application focused. Please click on the terminal to type 'enter' key and exit.");
                return;
            }
            System.out.print(String.format("\033[%dA", 1));
            System.out.print("\033[2K");
            System.out.print("-> ");
            System.out.println(key);
        };
        var keylogger = UsecaseFactory.createKeyLoggerRunner(onKeyPressed);
        keylogger.init();
        TerminalUtil.clear();
        TerminalUtil.setGoldColor();
        System.out.println("DummyCopilot");
        TerminalUtil.setPurpleColor();
        System.out.println("");
        System.out.println("Type any key to see the keycode on terminal. Type 'enter' to exit.");
        System.out.println("");
        TerminalUtil.setGoldColor();
        TerminalUtil.hideCursor();
        // TODO: Analisar se vale a pena manter esse console.readPass... eu poderia implementar
        // a mesma rotina bloqueante aqui no keylogger tbm.
        System.out.println("-> ");
        console.readPassword("");
        keylogger.stop();
        TerminalUtil.showCursor();
        TerminalUtil.clear();
        return Frame.INITIAL_FRAME;
    }
}
