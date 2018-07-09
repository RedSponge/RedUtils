package com.redsponge.redutils.version;

import com.redsponge.redutils.util.thread.ThreadPool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class VersionTracker {

    private static final String VERSION = "1.115";

    public static String fetchLatestVersion() {
        try {
            URL url = new URL("https://raw.githubusercontent.com/RedSponge/RedUtilsRemastered/master/version");
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

            String text = reader.readLine();
            reader.close();
            return text;
        } catch (IOException e) {
            System.out.println("Couldn't Read File! Check your internet connection");
            e.printStackTrace();
        }
        return "0";
    }

    public static void checkVersion(ThreadPool pool) {
        System.out.println("Checking versions");
        pool.runTask(() -> {
            String latest = fetchLatestVersion();
            if (latest.equals(VERSION)) {
                System.out.println("You are running the latest version (" + latest + ")");
            } else if (Double.parseDouble(latest) > Double.parseDouble(VERSION)) {
                System.err.println("A newer build is available: " + latest + " (you are running " + VERSION + ")");
            } else {
                System.out.println("If you got here.. congratz.. I have no idea how you did it.");
            }
        });
    }

}
