package de.larsgrefer.logi;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

import java.io.IOException;

@EnabledOnOs(value = OS.WINDOWS)
class LogiLedHandlerTest {

    @Test
    public void init() throws IOException {
        LogiLedHandler abcdefghij = new LogiLedHandler("ABCDEFGHXYZ");
        abcdefghij.close();
    }

}