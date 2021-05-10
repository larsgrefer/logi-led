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

    @Test
    public void testAvgColor() throws IOException {
        BufferedImage bufferedImage = ImageIO.read(getClass().getClassLoader().getResourceAsStream("screenshot_full2.png"));

        ColorService colorService = new ColorService();

        long start = System.nanoTime();
        for (int i = 0; i < 50; i++) {
            colorService.getAvgColor(bufferedImage);
        }
        Duration duration = Duration.ofNanos((System.nanoTime() - start) / 50);

        System.out.println(duration.toMillis());
    }
}