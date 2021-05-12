package de.larsgrefer.logi.jna;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.ptr.IntByReference;

import java.nio.charset.StandardCharsets;

public interface LogiLed extends Library {

    LogiLed INSTANCE = Native.load("LogitechLedEnginesWrapper", LogiLed.class);

    /**
     * {@code bool LogiLedInit();}
     */
    boolean LogiLedInit();

    /**
     * {@code bool LogiLedInitWithName(const char name[]);}
     */
    boolean LogiLedInitWithName(byte[] name);

    default boolean LogiLedInitWithName(String name) {
        return LogiLedInitWithName(name.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * {@code bool LogiLedGetSdkVersion(int *majorNum, int *minorNum, int *buildNum);}
     */
    boolean LogiLedGetSdkVersion(IntByReference majorNum, IntByReference minorNum, IntByReference buildNum);

    /**
     * {@code }
     */
    void LogiLedShutdown();
}
