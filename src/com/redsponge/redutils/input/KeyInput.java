package com.redsponge.redutils.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    private int KEYS_LISTENED_TO = 1024;
    private boolean[] pressed = new boolean[KEYS_LISTENED_TO];
    private boolean[] wasPressed = new boolean[KEYS_LISTENED_TO];

    @Override
    public void keyPressed(KeyEvent e) {
        pressed[e.getKeyCode()] = true;
    }

    public void tick() {
        for(int i = 0; i < KEYS_LISTENED_TO; i++) {
            wasPressed[i] = pressed[i];
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        pressed[e.getKeyCode()] = false;
    }

    public boolean isPressed(int key) {
        return pressed[key];
    }

    public boolean isReleased(int key) {
        return !pressed[key];
    }

    public boolean wasPressed(int key) {
        return pressed[key] && !wasPressed[key];
    }

    public boolean wasReleased(int key) {
        return !pressed[key] && wasPressed[key];
    }

    public void setKeysListenedTo(int keysListenedTo) {
        KEYS_LISTENED_TO = keysListenedTo;
        pressed = new boolean[keysListenedTo];
        wasPressed = new boolean[keysListenedTo];
    }
}
