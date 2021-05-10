package de.larsgrefer.logi;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class LogiLedHandlerTest {

    @Test
    public void init() throws IOException {
        LogiLedHandler abcdefghij = new LogiLedHandler("ABCDEFGHXYZ");
        abcdefghij.close();
    }

}