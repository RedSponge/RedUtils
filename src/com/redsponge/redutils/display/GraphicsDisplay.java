package com.redsponge.redutils.display;

import com.redsponge.redutils.GraphicsApp;

import javax.swing.JFrame;
import java.awt.Canvas;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.image.BufferStrategy;
import java.lang.reflect.Method;

public class GraphicsDisplay {

    private JFrame frame;
    private Canvas canvas;

    private String title;
    private int width, height;
    private GraphicsApp app;

    private int x, y;

    private boolean initiated;

    public GraphicsDisplay(GraphicsApp app, String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        this.app = app;
    }

    public void initiateFrame() {
        if(initiated) return;
        initiated = true;
        frame = new JFrame(title);
        addMainListener();
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

    private void addMainListener() {
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                setSize(e.getComponent().getWidth(), e.getComponent().getHeight());
                runGraphicsAppMethod(getGraphicsAppMethod("handleResize", int.class, int.class), width, height);
            }

            @Override
            public void componentMoved(ComponentEvent e) {
                x = e.getComponent().getX();
                y = e.getComponent().getY();
                runGraphicsAppMethod(getGraphicsAppMethod("handleMove", int.class, int.class), x, y);
            }

            @Override
            public void componentShown(ComponentEvent e) {
                runGraphicsAppMethod(getGraphicsAppMethod("handleShow"));
            }

            @Override
            public void componentHidden(ComponentEvent e) {
                runGraphicsAppMethod(getGraphicsAppMethod("handleHide"));
            }
        });

        frame.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                runGraphicsAppMethod(getGraphicsAppMethod("handleFocus", boolean.class), true);
            }

            @Override
            public void focusLost(FocusEvent e) {
                runGraphicsAppMethod(getGraphicsAppMethod("handleFocus", boolean.class), false);
            }
        });
    }

    private Method getGraphicsAppMethod(String name, Class<?>... params) {
        try {
            Method m = GraphicsApp.class.getDeclaredMethod(name, params);
            m.setAccessible(true);
            return m;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void runGraphicsAppMethod(Method m, Object... params) {
        try {
            m.invoke(app, params);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public Frame getFrame() {
        return frame;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public Graphics2D getGraphics() {
    	if(getBufferStrategy() == null) {
    		return null;
    	}
        return (Graphics2D) getBufferStrategy().getDrawGraphics();
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

    public void setSize(int width, int height) {
        setWidth(width);
        setHeight(height);
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

    public void setResizable(boolean resizable) {
        frame.setResizable(resizable);
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
