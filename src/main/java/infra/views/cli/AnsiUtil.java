package infra.views.cli;

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

    static void showCursor() {
        System.out.print("\033[?25h");
    }
}
