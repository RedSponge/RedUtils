package com.redsponge.redutils.input;

import com.redsponge.redutils.display.GraphicsDisplay;

public class InputManager {

    private KeyInput keyInput;
    private MouseInput mouseInput;

    @SuppressWarnings("unused")
    public InputManager(GraphicsDisplay display) {
        this(display, false);
    }

    public InputManager(GraphicsDisplay display, boolean register) {
        this.keyInput = new KeyInput();
        this.mouseInput = new MouseInput();
        if(register) registerToDisplay(display);
    }

    public void registerToDisplay(GraphicsDisplay display) {
        display.getFrame().addKeyListener(keyInput);
        display.getCanvas().addMouseListener(mouseInput);
        display.getCanvas().addMouseMotionListener(mouseInput);
    }

    public void tickKeys() {
        keyInput.tick();
    }

    public void tickMouse() {
        mouseInput.tick();
    }

    public boolean isKeyHeld(int key) {
        return keyInput.isPressed(key);
    }

    public boolean isKeyJustPressed(int key) {
        return keyInput.wasPressed(key);
    }

    public boolean isKeyReleased(int key) {
        return keyInput.isReleased(key);
    }

    public boolean isKeyJustReleased(int key) {
        return keyInput.wasReleased(key);
    }

    public boolean isMouseButtonHeld(int button) {
        return mouseInput.isPressed(button);
    }

    public boolean isMouseButtonJustPressed(int button) {
        return mouseInput.wasPressed(button);
    }

    public boolean isMouseButtonReleased(int button) {
        return mouseInput.isReleased(button);
    }

    public boolean isMouseButtonJustReleased(int button) {
        return mouseInput.wasReleased(button);
    }

    public int getMouseX() {
        return mouseInput.getMouseX();
    }

    public int getMouseY() {
        return mouseInput.getMouseY();
    }



}
