package controller;

import java.awt.event.KeyEvent;
import input.KeyInputs;

public class PlayerController implements Controller {

    private KeyInputs input;

    public PlayerController(KeyInputs input) {
        this.input = input;
    }


    @Override
    public boolean isRequestingUp() {
        return input.isPressed(KeyEvent.VK_W);
    }

    @Override
    public boolean isRequestingDown() {
        return input.isPressed(KeyEvent.VK_S);
    }

    @Override
    public boolean isRequestingLeft() {
        return input.isPressed(KeyEvent.VK_A);
    }

    @Override
    public boolean isRequestingRight() {
        return input.isPressed(KeyEvent.VK_D);
    }

    @Override
    public boolean isSprinting() {
        return input.isPressed(KeyEvent.VK_SHIFT);
    }

    @Override
    public boolean isSneaking() {
        // for windows and mac users
        String OS = System.getProperty("os.name").toLowerCase();
        if (OS.contains("win")) {
            return input.isPressed(KeyEvent.VK_CONTROL);
        } else {
            return input.isPressed(KeyEvent.VK_META);
        }

    }

    @Override
    public boolean isSprintKeyReleased() {
        return input.isReleased(KeyEvent.VK_SHIFT);
    }

    @Override
    public boolean isPaused() {
        return input.isPressed(KeyEvent.VK_ESCAPE);
    }


}