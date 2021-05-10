package de.larsgrefer.logi.cv;

import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.Duration;

public class ScreenCaptureTest {
    @Test
    public void RunDo() throws IOException {
        ScreenCapture capture = new ScreenCapture();

        BufferedImage bufferedImage = null;
        for (int i = 0; i < 50; i++) {
            //bufferedImage = capture.capture();
        }


        long start = System.nanoTime();
        for (int i = 0; i < 100; i++) {
            //bufferedImage = capture.capture();
        }
        long end = System.nanoTime();
        System.out.println("Took: " + Duration.ofNanos((end - start) / 100).toMillis());

        //ImageIO.write(bufferedImage, "png", new File("test.png"));

    }
}
