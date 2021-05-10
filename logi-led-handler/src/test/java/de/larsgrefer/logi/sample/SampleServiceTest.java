package de.larsgrefer.logi.sample;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class SampleServiceTest {

    @Test
    void getScreens() {

        for (GraphicsDevice screenDevice : GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()) {
            System.out.println(screenDevice.getIDstring());
            System.out.println(screenDevice.getDisplayMode());
            System.out.println(screenDevice.getDefaultConfiguration().getBounds());

            System.out.println();
        }
    }
}