package de.larsgrefer.logi.jna;

import com.sun.jna.ptr.IntByReference;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LogiLedTest {

    @Test
    void getVersion() {
        IntByReference major = new IntByReference();
        IntByReference minor = new IntByReference();
        IntByReference build = new IntByReference();

        assertTrue(LogiLed.INSTANCE.LogiLedInit());

        boolean b = LogiLed.INSTANCE.LogiLedGetSdkVersion(major, minor, build);

        assertTrue(b);

        System.out.println(String.format("%d.%d.%d", major.getValue(), minor.getValue(), build.getValue()));
    }

}