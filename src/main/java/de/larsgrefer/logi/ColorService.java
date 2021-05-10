package de.larsgrefer.logi;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class ColorService {

    public static Color getAvgColor(int[] rgbPixels) {
        long r = 0;
        long g = 0;
        long b = 0;

        int counter = 0;

        for (int rgbPixel : rgbPixels) {
            Color color = new Color(rgbPixel);
            r += color.getRed();
            g += color.getGreen();
            b += color.getBlue();
            counter++;
        }

        int red = (int) (r / counter);
        int green = (int) (g / counter);
        int blue = (int) (b / counter);

        return new Color(red, green, blue);

    }

    public static Color getAvgColor(BufferedImage image) {
        long r = 0;
        long g = 0;
        long b = 0;

        int counter = 0;

        AffineTransform at = new AffineTransform();
        at.scale(0.1, 0.1);
        AffineTransformOp scaleDown = new AffineTransformOp(at, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);

        image = scaleDown.filter(image, null);

        int width = image.getWidth();
        int height = image.getHeight();

        int xStep = 1;
        int yStep = 1;

        if (width > 50) {
            xStep = width / 50;
        }

        if (height > 50) {
            yStep = height / 50;
        }

        for (int x = 0; x < width; x += xStep) {
            for (int y = 0; y < height; y += yStep) {
                Color color = new Color(image.getRGB(x, y));
                r += color.getRed();
                g += color.getGreen();
                b += color.getBlue();
                counter++;
            }
        }

        int red = (int) (r / counter);
        int green = (int) (g / counter);
        int blue = (int) (b / counter);

        return new Color(red, green, blue);
    }

    public static Color applyBrightnessFactor(Color base, float factor) {
        float[] floats = Color.RGBtoHSB(base.getRed(), base.getGreen(), base.getBlue(), null);

        float h = floats[0];
        float s = floats[1];
        float b = floats[2];

        b = Math.min(b * factor, 1f);

        return Color.getHSBColor(h, s, b);
    }

    public static Color applyFactors(Color base, float brightnessFactor, float saturationFactor) {
        float[] floats = Color.RGBtoHSB(base.getRed(), base.getGreen(), base.getBlue(), null);

        float h = floats[0];
        float s = floats[1];
        float b = floats[2];

        s = Math.min(s * saturationFactor, 1f);
        b = Math.min(b * brightnessFactor, 1f);

        return Color.getHSBColor(h, s, b);
    }

}
