package com.redsponge.redutils.gfx;

import com.redsponge.redutils.console.ConsoleMSG;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

public class Texture {

    private BufferedImage image;
    private static final Logger logger = new Logger();
    public Texture(String path) {
        load(path);
    }

    private void load(String path) {
        if(logger.loaded.get(path) != null) {
            image = logger.loaded.get(path);
            return;
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
            image = ImageIO.read(getClass().getResourceAsStream(finalPath));
            logger.logLoad(path);
            logger.loaded.put(path, image);
        } catch(IllegalArgumentException e) {
            throw new RuntimeException("Couldn't find texture at \"" + path + "\"");
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getImage() {
        return image;
    }

    public static void doLogging(boolean log) {
        logger.logLoads = log;
    }


    private static class Logger {

        private Logger() {}
        private final HashMap<String, BufferedImage> loaded = new HashMap<String, BufferedImage>();
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
