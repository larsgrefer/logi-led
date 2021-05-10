package de.larsgrefer.logi.sampler;

import de.larsgrefer.logi.ColorService;
import de.larsgrefer.logi.DelayedColorSetter;
import de.larsgrefer.logi.LogiLedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.awt.*;
import java.awt.image.BufferedImage;

@Service
public class ScreenSampler {


    @Autowired
    private LogiLedHandler ledHandler;

    @Autowired
    private Robot robot;

    @Autowired
    private TaskScheduler taskScheduler;

    @Autowired
    private SamplerProperties samplerProperties;

    DelayedColorSetter frontLeft;
    DelayedColorSetter backLeft;
    DelayedColorSetter frontRight;
    DelayedColorSetter backRight;

    @PostConstruct
    public void init() {
        frontLeft = new DelayedColorSetter(this::setFrontLeftColor);
        backLeft = new DelayedColorSetter(this::setRearLeftColor);
        frontRight = new DelayedColorSetter(this::setFrontRightColor);
        backRight = new DelayedColorSetter(this::setRearRightColor);


        taskScheduler.scheduleAtFixedRate(this::updateLeds, samplerProperties.getRate());
    }

    public void updateLeds() {
        GraphicsDevice screenDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[1];

        Rectangle bounds = screenDevice.getDefaultConfiguration().getBounds();

        BufferedImage screen = robot.createScreenCapture(bounds);

        handleScreen(screen);
    }

    private void handleScreen(BufferedImage screen) {
        int x = screen.getWidth() / 2;
        int y = screen.getHeight() / 2;

        BufferedImage topLeft = screen.getSubimage(0, 0, x, y);
        Color topLeftColor = ColorService.getAvgColor(topLeft);
        setRearLeftColor(topLeftColor);

        BufferedImage bottomLeft = screen.getSubimage(0, y, x, y);
        Color bottomLeftColor = ColorService.getAvgColor(bottomLeft);
        setFrontLeftColor(bottomLeftColor);

        BufferedImage topRight = screen.getSubimage(x, 0, x, y);
        Color topRightColor = ColorService.getAvgColor(topRight);
        setRearRightColor(topRightColor);

        BufferedImage bottomRight = screen.getSubimage(x, y, x, y);
        Color bottomRightColor = ColorService.getAvgColor(bottomRight);
        setFrontRightColor(bottomRightColor);
    }

    public void setFrontLeftColor(Color color) {
        setColor(0, color);
    }

    public void setRearLeftColor(Color color) {
        setColor(2, color);
    }

    public void setFrontRightColor(Color color) {
        setColor(1, color);
    }

    public void setRearRightColor(Color color) {
        setColor(3, color);
    }

    public void setColor(int zone, Color color) {

        double brightnesFactor = ledHandler.getConfigOptionNumber("Faktor/Brightness", 100d) / 100d;
        double saturationFactor = ledHandler.getConfigOptionNumber("Faktor/Saturation", 100d) / 100d;

        color = ColorService.applyFactors(color, (float) brightnesFactor, (float) saturationFactor);

        ledHandler.setLightingForTargetZone(LogiLedHandler.DeviceType.Speaker, zone, color);
    }
}
