package services.shortcuts_manager;

public interface IRobot {
    void keyPress(int keycode);
    void keyRelease(int keycode);
    void delay(int ms);
}
