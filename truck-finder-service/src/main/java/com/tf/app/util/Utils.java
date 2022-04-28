package com.tf.app.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Utils {
    public static Number getNoOfLines(String fileName) throws IOException {
        if (fileName == null || fileName.isEmpty())
            return 0;
        Path path = Paths.get(fileName);
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
