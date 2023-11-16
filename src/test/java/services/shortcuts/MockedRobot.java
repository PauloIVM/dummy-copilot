package services.shortcuts;
import java.util.ArrayList;

import services.shortcuts_manager.IRobot;

public class MockedRobot implements IRobot {
    private ArrayList<Integer> keysTyped;

    public MockedRobot() throws Exception {
        this.keysTyped = new ArrayList<Integer>();
    }

    public ArrayList<Integer> getKeysTyped() {
        return this.keysTyped;
    }

    public void keyRelease(int keycode) {
        this.keysTyped.add(keycode);
    }

    public void keyPress(int keycode) {
        this.keysTyped.add(keycode);
    }

    public void delay(int ms) {}
}
