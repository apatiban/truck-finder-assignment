package com.tf.app.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Utils {

    public static Number getNoOfLines(URL url) throws IOException {
        int count = 0;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()))) {
            while ((br.readLine()) != null) {
                count++;
            }
        }
        return count;
    }

    public static Number getNoOfLines(String fileName) throws IOException {

        if (fileName == null || fileName.isEmpty())
            return 0;
        Path path = null;
        try {

            path = Paths.get(new URI("https://data.sfgov.org/api/views/rqzj-sfat/rows.csv"));
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        long lines = 0;
        lines = Files.lines(path).count();
        return lines;

    }

    public static double getDouble(String value) {
        if (null == value || value.isEmpty())
            return 0.0;
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    public static Long getLong(String value) {
        if (null == value || value.isEmpty())
            return 0L;
        try {
            return Long.parseLong(value);
        } catch (NumberFormatException e) {
            return 0L;
        }
    }
}
