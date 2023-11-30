package infra.views.cli;

import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

public class AnsiUtil {
    static void clear() {
        System.out.print("\033[H\033[2J");
    }
    
    static void resetColors() {
        System.out.print("\033[0m");
    }

    static void hideInput() {
        System.out.print("\033[0;30m");
        System.out.print("\033[40m");
    }

    static void clearCurrLine() {
        System.out.print("\033[2K"); 
    }

    static void moveLinesUp(int numberOfLines) {
        System.out.print(String.format("\033[%dA", numberOfLines));
    }

    static void setGoldColor() {
        System.out.print("\033[38;5;220m");
    }

    static void setGreenColor() {
        System.out.print("\033[38;5;2m");
    }

    static void setPurpleColor() {
        System.out.print("\033[38;5;213m");
    }

    static void hideCursor() {
        System.out.print("\033[?25l");
    }

    // INFO: This method avoid some bugs when I use the keyEventsScanner together with
    // native scanner
    static void clearTerminalBuffer() {
        try {
            Terminal terminal = TerminalBuilder.terminal();
            LineReader lineReader = LineReaderBuilder.builder().terminal(terminal).build();
            String linha = lineReader.readLine();
            if (!linha.isEmpty()) lineReader.callWidget(LineReader.CLEAR_SCREEN);
        } catch (Exception e) {}
    }

    static void showCursor() {
        System.out.print("\033[?25h");
    }
}
