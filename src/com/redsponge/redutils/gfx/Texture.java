package com.redsponge.redutils.gfx;

import com.redsponge.redutils.console.ConsoleMSG;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

public class Texture {

    private BufferedImage image;
    private static final Logger logger = new Logger();
    private Texture(BufferedImage image) {
        this.image = image;
    }

    public static Texture load(String path) {
        if(logger.loaded.get(path) != null) {
            return logger.loaded.get(path);
        }
        try {
            String finalPath = "";
            if(path.endsWith(".png")) {
                finalPath = "/" + path;
            } else if(!path.contains(".")) {
                finalPath = "/" + path + ".png";
            } else {
                finalPath = "/" + path;
            }
            Texture t = new Texture(ImageIO.read(Texture.class.getResourceAsStream(finalPath)));
            logger.logLoad(path);
            logger.loaded.put(path, t);
            return t;
        } catch(IllegalArgumentException e) {
            throw new RuntimeException("Couldn't find texture at \"" + path + "\"");
        } catch(IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public BufferedImage getImage() {
        return image;
    }


    /* ###############################################
        render(Graphics2D g, int x, int y) variations!
       ############################################### */
    public void render(Graphics2D g, int x, int y) {
        g.drawImage(image, x, y, null);
    }

    public void render(Graphics2D g, int x, int y, boolean centered) {
        if(centered) g.drawImage(image, x - image.getWidth() / 2, y - image.getHeight() / 2, image.getWidth(), image.getHeight(),null);
        else render(g, x, y);
    }

    public void render(Graphics2D g, int x, int y, boolean centered, int angle) {
        render(g, x, y, centered, Math.toRadians(angle));
    }

    public void render(Graphics2D g, int x, int y, boolean centered, double radians) {
        AffineTransform transform = new AffineTransform();
        transform.rotate(radians, x, y);
        AffineTransform backup = g.getTransform();

        g.setTransform(transform);
        render(g, x, y, centered);
        g.setTransform(backup);

//        String s = "hellox";
//        StringBuilder b = new StringBuilder(s);
//        b.setCharAt(5, '!');
//        s = b.toString();
    }


    /* ######################################################################
        render(Graphics2D g, int x, int y, int width, int height) variations!
       ###################################################################### */

    public void render(Graphics2D g, int x, int y, int width, int height) {
        g.drawImage(image, x, y, width, height, null);
    }

    public void render(Graphics2D g, int x, int y, int width, int height, boolean centered) {
        if(centered) g.drawImage(image, x - width / 2, y - height / 2, width, height, null);
        else render(g, x, y, width, height);
    }

    public void render(Graphics2D g, int x, int y, int width, int height, boolean centered, double radians) {
        AffineTransform transform = new AffineTransform();
        transform.rotate(radians, x, y);
        AffineTransform backup = g.getTransform();

        g.setTransform(transform);
        render(g, x, y, width, height, centered);
        g.setTransform(backup);
    }

    public void render(Graphics2D g, int x, int y, int width, int height, boolean centered, int angle) {
        render(g, x, y, width, height, centered, Math.toRadians(angle));
    }

    /* ########################################################################################
        render(Graphics2D g, int x, int y, float widthPercent, float heightPercent) and
        render(Graphics2D g, int x, int y, float sizePercent) variations!
       ######################################################################################## */

    public void render(Graphics2D g, int x, int y, float sizePercent) {
        render(g, x, y, sizePercent, sizePercent);
    }

    public void render(Graphics2D g, int x, int y, float sizePercent, boolean center) {
        render(g, x, y, sizePercent, sizePercent, center);
    }

    public void render(Graphics2D g, int x, int y, float sizePercent, boolean center, double radians) {
        render(g, x, y, sizePercent, sizePercent, center, radians);
    }

    public void render(Graphics2D g, int x, int y, float sizePercent, boolean center, int angle) {
        render(g, x, y, sizePercent, sizePercent, center, angle);
    }

    public void render(Graphics2D g, int x, int y, float widthPercent, float heightPercent) {
        render(g, x, y, (int) (image.getWidth() * widthPercent), (int) (image.getHeight() * heightPercent));
    }

    public void render(Graphics2D g, int x, int y, float widthPercent, float heightPercent, boolean centered) {
        if(centered) render(g, x - (int)(image.getWidth() * widthPercent) / 2, y - (int)(image.getHeight() * heightPercent) / 2, (int)(image.getWidth() * widthPercent), (int)(image.getHeight() * heightPercent));
        else render(g, x, y, widthPercent, heightPercent);
    }

    public void render(Graphics2D g, int x, int y, float widthPercent, float heightPercent, boolean centered, double radians) {
        AffineTransform transform = new AffineTransform();
        transform.rotate(radians, x, y);
        AffineTransform backup = g.getTransform();

        g.setTransform(transform);
        render(g, x, y, widthPercent, heightPercent, centered);
        g.setTransform(backup);
    }

    public void render(Graphics2D g, int x, int y, float widthPercent, float heightPercent, boolean centered, int angle) {
        render(g, x, y, widthPercent, heightPercent, centered, Math.toRadians(angle));
    }

    public static void doLogging(boolean log) {
        logger.logLoads = log;
    }

    private static class Logger {

        private Logger() {}
        private final HashMap<String, Texture> loaded = new HashMap<String, Texture>();
        private boolean logLoads = false;

        public void logLoad(String path) {
            logLoad(path, false);
        }

        public void logLoad(String path, boolean bypassLogLoads) {
            if(bypassLogLoads || logLoads)
                ConsoleMSG.INFO.info("Loaded texture at \"" + path + "\".");
        }

    }
}
