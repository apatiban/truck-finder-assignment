package com.ft.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Utils {
    public static Number getNoOfLines(String fileName) {
        if (fileName == null || fileName.isEmpty())
            return 0;
        Path path = Paths.get(fileName);
        long lines = 0;

        try {
            lines = Files.lines(path).count();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
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
}
