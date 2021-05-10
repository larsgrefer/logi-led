package de.larsgrefer.logi.jna;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.ptr.IntByReference;

public interface LogiLed extends Library {

    LogiLed INSTANCE = Native.load("LogitechLedEnginesWrapper ", LogiLed.class);

    boolean LogiLedInit();

    //bool LogiLedGetSdkVersion(int *majorNum, int *minorNum, int *buildNum);
    boolean LogiLedGetSdkVersion(IntByReference majorNum, IntByReference minorNum, IntByReference buildNum);
}
