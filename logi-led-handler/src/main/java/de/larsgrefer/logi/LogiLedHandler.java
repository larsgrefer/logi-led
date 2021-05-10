package de.larsgrefer.logi;

import com.logitech.gaming.LogiLED;
import lombok.RequiredArgsConstructor;

import java.awt.*;
import java.io.Closeable;
import java.util.EnumSet;

public class LogiLedHandler implements Closeable {

    public LogiLedHandler() {
        LogiLED.LogiLedInit();
    }

    public LogiLedHandler(String name) {
        if (!LogiLED.LogiLedInitWithName(LogiLedUtils.getFixedCharArray(name))) {
            throw new IllegalStateException("LogiLedInitWithName returned false");
        }
    }

    @Override
    public void close() {
        LogiLED.LogiLedShutdown();
    }

    public double getConfigOptionNumber(String name, double defaultValue) {
        LogiLedUtils.validateConfigName(name);

        LogiLED.LogiLedSetConfigOptionLabel(name, name);

        return LogiLED.LogiLedGetConfigOptionNumber(name, defaultValue);
    }

    public synchronized void setLightingForTargetZone(DeviceType type, int zone, Color color) {
        setLightingForTargetZone(type.value, zone, color);
    }

    public synchronized void setLightingForTargetZone(EnumSet<DeviceType> types, int zone, Color color) {
        int type = 0;

        for (DeviceType deviceType : types) {
            type |= deviceType.value;
        }

        setLightingForTargetZone(type, zone, color);
    }

    public synchronized void setLightingForTargetZone(int type, int zone, Color color) {
        int[] apiColorValues = LogiLedUtils.getApiColorValues(color);

        boolean success = LogiLED.LogiLedSetLightingForTargetZone(type, zone, apiColorValues[0], apiColorValues[1], apiColorValues[2]);
        if (!success) {
            throw new IllegalStateException("LogiLedSetLightingForTargetZone returned false");
        }
    }

    @RequiredArgsConstructor
    public enum DeviceType {
        Keyboard(0x0),
        Mouse(0x3),
        Mousemat(0x4),
        Headset(0x8),
        Speaker(0xE);

        private final int value;
    }
}
