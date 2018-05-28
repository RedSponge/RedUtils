package com.redsponge.redutils.input;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter {

    private int BUTTONS_LISTENED_TO = 10;
    private boolean[] buttons = new boolean[BUTTONS_LISTENED_TO+1];
    private boolean[] werePressed = new boolean[BUTTONS_LISTENED_TO+1];

    private int x;
    private int y;

    @Override
    public void mouseMoved(MouseEvent e) {
        x = e.getX();
        y = e.getY();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        buttons[e.getButton()] = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        buttons[e.getButton()] = false;
    }

    public void tick() {
        for(int i = 0; i < BUTTONS_LISTENED_TO+1; i++) {
            werePressed[i] = buttons[i];
        }
    }

    private void checkAvaliability(int button) {
        if(BUTTONS_LISTENED_TO < button) {
            throw new RuntimeException("Not listening to button \'" + button + "\', try using MouseInput#setButtonsListenedTo");
        }
    }

    public boolean isPressed(int button) {
        checkAvaliability(button);
        return buttons[button];
    }

    public boolean wasPressed(int button) {
        checkAvaliability(button);
        return buttons[button] && !werePressed[button];
    }

    public boolean isReleased(int button) {
        checkAvaliability(button);
        return !buttons[button];
    }

    public boolean wasReleased(int button) {
        checkAvaliability(button);
        return !buttons[button] && werePressed[button];
    }

    public void setButtonsListenedTo(int BUTTONS_LISTENED_TO) {
        this.BUTTONS_LISTENED_TO = BUTTONS_LISTENED_TO;
        buttons = new boolean[BUTTONS_LISTENED_TO+1];
        werePressed = new boolean[BUTTONS_LISTENED_TO+1];
    }

    public int getMouseX() {
        return x;
    }

    public int getMouseY() {
        return y;
    }
}
