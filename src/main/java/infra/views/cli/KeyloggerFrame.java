package infra.views.cli;

import entities.keyId.KeyId;
import adapters.keyIdAdapter.KeyIdAdapter;
import infra.composers.UsecaseFactory;

import java.util.Scanner;

class KeyloggerFrame implements IFrame {
    public Frame run(Scanner scan) {
        TerminalUtil.clear();
        TerminalUtil.setGoldColor();
        System.out.println("DummyCopilot");
        TerminalUtil.setPurpleColor();
        System.out.println("");
        System.out.println("Type any key to see the keycode on terminal. Type 'enter' to exit.");
        System.out.println("");
        TerminalUtil.setGoldColor();
        TerminalUtil.hideCursor();
        System.out.println("-> ");
        var keyScanner = UsecaseFactory.createKeyScanner();
        while (true) {
            var key = keyScanner.next();
            if (key.keyId != null && key.keyId.equals(KeyId.VK_ENTER)) break;
            String keyText = KeyIdAdapter.parseKeyIdToText(key.keyId);
            System.out.print(String.format("\033[%dA", 1));
            System.out.print("\033[2K");
            System.out.print("-> ");
            System.out.println(keyText);
        }
        keyScanner.close();
        TerminalUtil.showCursor();
        return Frame.INITIAL_FRAME;
    }
}
