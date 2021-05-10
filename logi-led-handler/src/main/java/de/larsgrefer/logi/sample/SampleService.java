package de.larsgrefer.logi.sample;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SampleService {

    private final Robot robot = new Robot();

    public SampleService() throws AWTException {
    }

    public List<String> getScreens() {
        return Arrays.stream(GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()).
                map(GraphicsDevice::getIDstring)
                .collect(Collectors.toList());
    }

    public BufferedImage getScreen(int i) {
        GraphicsDevice screenDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[i];

        Rectangle bounds = screenDevice.getDefaultConfiguration().getBounds();

        return robot.createScreenCapture(bounds);
    }
}
