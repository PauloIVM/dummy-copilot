package infra.views.cli;

import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

public class TerminalUtil {
    static private Terminal terminal = null;

    static void clear() {
        System.out.print("\033[H\033[2J");
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
        TerminalUtil.showInput();
        System.out.print("\033[?25h");
    }

    static void hideCursor() {
        TerminalUtil.hideInput();
        System.out.print("\033[?25l");
    }

    static private void showInput() {
        TerminalUtil.buildTerminal();
        TerminalUtil.terminal.echo(true);
    }

    static private void hideInput() {
        TerminalUtil.buildTerminal();
        TerminalUtil.terminal.echo(false);
    }

    static private void buildTerminal() {
        if (TerminalUtil.terminal == null) {
            try {
                TerminalUtil.terminal = TerminalBuilder.terminal();
            } catch (Exception e) {}
        }
    }
}
