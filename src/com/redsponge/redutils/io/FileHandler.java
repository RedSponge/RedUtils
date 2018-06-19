package com.redsponge.redutils.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileHandler {

    public static String readFile(String path) {
        path = "/" + path;

        InputStream in = FileHandler.class.getResourceAsStream(path);
        InputStreamReader inr = new InputStreamReader(in);
        BufferedReader reader = new BufferedReader(inr);
        String full = "";
        String line = "";
        try {
            while ((line = reader.readLine()) != null) {
                full += line + "\n";
            }
            reader.close();

            return full;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
