package de.larsgrefer.logi;

import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.Duration;

class ColorServiceTest {

    @Test
    void applyBrightnessFactor() {

        Color darkRed = new Color(0.5f, 0f, 0f);

        Color red = ColorService.applyBrightnessFactor(darkRed, 20);

        System.out.println(red.getRed());
    }
}