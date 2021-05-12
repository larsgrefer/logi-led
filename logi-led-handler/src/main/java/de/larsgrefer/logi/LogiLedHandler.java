package de.larsgrefer.logi;

import com.logitech.gaming.LogiLED;
import lombok.RequiredArgsConstructor;

import java.awt.*;
import java.io.Closeable;
import java.util.EnumSet;

public class LogiLedHandler implements Closeable {

    public LogiLedHandler() {
        if (!LogiLED.LogiLedInit()) {
            throw new IllegalStateException("LogiLedInit returned false");
        }
    }

    public LogiLedHandler(String name) {
        if (!LogiLED.LogiLedInitWithName(LogiLedUtils.getFixedCharArray(name))) {
            throw new IllegalStateException("LogiLedInitWithName returned false");
        }
        try {
            Thread.sleep(2);
        } catch (InterruptedException ignored) {
        }
    }

    @Override
    public void close() {
        LogiLED.LogiLedShutdown();
    }

    public double getConfigOptionNumber(String name, double defaultValue) {
        LogiLedUtils.validateConfigName(name);

        return LogiLED.LogiLedGetConfigOptionNumber(name, defaultValue);
    }

    public void setLightingForTargetZone(DeviceType type, int zone, Color color) {
        setLightingForTargetZone(type.value, zone, color);
    }

    public void setLightingForTargetZone(EnumSet<DeviceType> types, int zone, Color color) {
        int type = 0;

        for (DeviceType deviceType : types) {
            type |= deviceType.value;
        }

        setLightingForTargetZone(type, zone, color);
    }

    public void setLightingForTargetZone(int type, int zone, Color color) {
        int[] apiColorValues = LogiLedUtils.getApiColorValues(color);

        boolean success = LogiLED.LogiLedSetLightingForTargetZone(type, zone, apiColorValues[0], apiColorValues[1], apiColorValues[2]);
        if (!success) {
            throw new IllegalStateException("LogiLedSetLightingForTargetZone returned false");
        }
    }

    @RequiredArgsConstructor
    public enum DeviceType {
        Keyboard(LogiLED.DeviceType_Keyboard),
        Mouse(LogiLED.DeviceType_Mouse),
        Mousemat(LogiLED.DeviceType_Mousemat),
        Headset(LogiLED.DeviceType_Headset),
        Speaker(LogiLED.DeviceType_Speaker);

        private final int value;
    }
}
