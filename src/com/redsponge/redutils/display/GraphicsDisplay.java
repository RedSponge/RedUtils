package com.redsponge.redutils.display;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class GraphicsDisplay {

    private JFrame frame;
    private Canvas canvas;

    private String title;
    private int width, height;

    public GraphicsDisplay(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;

        initiateFrame();
    }

    private void initiateFrame() {
        frame = new JFrame(title);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        canvas = new Canvas();
        canvas.setSize(width, height);
        canvas.setFocusable(false);

        frame.add(canvas);
        frame.pack();
        frame.setVisible(true);
    }

    public Frame getFrame() {
        return frame;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public Graphics getGraphics() {
    	if(getBufferStrategy() == null) {
    		return null;
    	}
        return getBufferStrategy().getDrawGraphics();
    }

    public void setTitle(String title) {
        this.title = title;
        frame.setTitle(title);
    }

    public void setWidth(int width) {
        this.width = width;
        updateSize();
    }

    public void setHeight(int height) {
        this.height = height;
        updateSize();
    }

    public String getTitle() {
        return title;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    private void updateSize() {
        frame.setSize(width, height);
    }

    public void clear() {
        getGraphics().clearRect(0, 0, width, height);
    }

    private BufferStrategy getBufferStrategy() {
        if(canvas.getBufferStrategy() == null){
            canvas.createBufferStrategy(3);
            return null;
        }
        return canvas.getBufferStrategy();
    }

    public void push() {
        getBufferStrategy().show();
        getGraphics().dispose();
    }
}
