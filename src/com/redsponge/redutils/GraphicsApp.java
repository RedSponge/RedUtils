package com.redsponge.redutils;

import java.awt.Graphics;

import com.redsponge.redutils.display.GraphicsDisplay;
import com.redsponge.redutils.util.ThreadPool;

public abstract class GraphicsApp {

    protected GraphicsDisplay display;
    protected Graphics g;

    protected String title;
    protected int width, height;
    private int tps, rps;

    protected ThreadPool threadPool;
    
    @SuppressWarnings("unused")
	private IntervalTimer ticksTimer;
    @SuppressWarnings("unused")
    private IntervalTimer rendersTimer;

    private boolean printTicksPerSecond, printRendersPerSecond;
    
    private boolean running;
    public static final int minThreads = 2;
    public static final int defThreads = 4;
    /**
     * @param title The title of the frame
     * @param width The width of the frame
     * @param height The height of the frame
     * @param tps Ticks Per Second
     * @param rps Renders Per Second
     * @param printTicksPerSecond if true the ticks per second would be printed to the console
     * @param printRendersPerSecond if true the renders per second would be printed to the console
     * @param numThreads number of threads to run on, defaults to defThreads if not present (on shortened constructor)
     * @throws NotEnoughThreadsPresentException When numThreads is less than @see minThreads
     */
    public GraphicsApp(String title, int width, int height, int tps, int rps, boolean printTicksPerSecond, boolean printRendersPerSecond, int numThreads) throws NotEnoughThreadsPresentException {
        display = new GraphicsDisplay(title, width, height);
        this.title = title;
        this.width = width;
        this.height = height;
        this.tps = tps;
        this.rps = rps;
        if(numThreads < minThreads) {
        	throw new NotEnoughThreadsPresentException(numThreads);
        }
        this.threadPool = new ThreadPool(numThreads);

        this.printTicksPerSecond = printTicksPerSecond;
        this.printRendersPerSecond = printRendersPerSecond;

        this.g = display.getGraphics();
    }

    public GraphicsApp(String title, int width, int height, int tps, int rps) throws NotEnoughThreadsPresentException {
        this(title, width, height, tps, rps, false, false, defThreads);
    }

    /**
     * Call this to start the program
     */
    public void start() {
    	if(running) {
    		return;
    	}
    	running = true;
    	preInit();
        init();
        this.ticksTimer = new IntervalTimer(() -> fullTick(), threadPool, tps, printTicksPerSecond, "Ticking");
        this.rendersTimer = new IntervalTimer(() -> fullRender(), threadPool, rps, printRendersPerSecond, "Rendering");
        postInit();
    }
    
    public void stop() {
    	if(!running) {
    		return;
    	}
    	running = false;
    	System.exit(0);
    }

    public abstract void init();

    public abstract void tick();

    public abstract void render();

    private void fullRender() {
    	if(display.getGraphics() == null) {
    		return;
    	}
    	g = display.getGraphics();
        display.clear();
        preRender();
        render();
        postRender();
        display.push();
    }

    public void preTick() {}
    public void postTick() {}
    
    public void preRender() {}
    public void postRender() {}
    
    public void preInit() {}
    /**
     * <strong>Called after IntervalTimers for ticking and rendering are created</strong>
     */
    public void postInit() {}
    
    public void postTimersCreated() {}
    
    private void fullTick() {
    	preTick();
        tick();
        postTick();
    }

    public GraphicsDisplay getDisplay() {
        return display;
    }
}

class NotEnoughThreadsPresentException extends RuntimeException {
	
	public NotEnoughThreadsPresentException(int numThreads) {
		super("Not enough threads (" + numThreads + ") are present! you should have at least " + GraphicsApp.minThreads);
	}
	
}
