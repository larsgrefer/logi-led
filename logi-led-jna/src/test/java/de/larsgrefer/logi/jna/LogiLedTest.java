package de.larsgrefer.logi.jna;

import com.sun.jna.ptr.IntByReference;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

import static org.junit.jupiter.api.Assertions.*;

@EnabledOnOs(value = OS.WINDOWS)
class LogiLedTest {

    @BeforeEach
    void init() {
        assertTrue(LogiLed.INSTANCE.LogiLedInitWithName(getClass().getName()));
    }

    @AfterEach
    void cleanup() {
        LogiLed.INSTANCE.LogiLedShutdown();
    }

    @Test
    void getVersion() {
        IntByReference major = new IntByReference();
        IntByReference minor = new IntByReference();
        IntByReference build = new IntByReference();

        boolean b = LogiLed.INSTANCE.LogiLedGetSdkVersion(major, minor, build);

        assertTrue(b);

        System.out.println(String.format("%d.%d.%d", major.getValue(), minor.getValue(), build.getValue()));
    }


}