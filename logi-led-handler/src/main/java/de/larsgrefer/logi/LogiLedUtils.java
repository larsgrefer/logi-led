package de.larsgrefer.logi;

import com.sun.tools.javac.util.StringUtils;
import lombok.experimental.UtilityClass;

import java.awt.*;
import java.nio.charset.StandardCharsets;

@UtilityClass
public class LogiLedUtils {

    public static char[] getFixedCharArray(String name) {
        byte[] bytes = name.getBytes(StandardCharsets.UTF_8);

        char[] charsForLogi = new char[bytes.length / 2 + 1];

        for (int i = 0; i < bytes.length; i++) {
            int index = i / 2;
            charsForLogi[index] = (char) (bytes[i] << 8 | charsForLogi[index] >> 8);
        }

        charsForLogi[charsForLogi.length - 1] = (char) (charsForLogi[charsForLogi.length - 1] >> 8);
        return charsForLogi;
    }

    public static int[] getApiColorValues(Color color) {
        int[] result = new int[3];

        result[0] = (int) (color.getRed() / 255d * 100);
        result[1] = (int) (color.getGreen() / 255d * 100);
        result[2] = (int) (color.getBlue() / 255d * 100);

        return result;
    }

    public static void validateConfigName(String name) {
        int first = name.indexOf("/");
        int last = name.lastIndexOf("/");

        if (first != last) {
            throw new IllegalArgumentException("Option Name " + name + " must not contain two forward slashes");
        }
    }
}
