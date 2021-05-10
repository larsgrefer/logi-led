package de.larsgrefer.logi;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LogiLedUtilsTest {

    @Test
    void getApiColorValues() {

        int[] apiColorValues = LogiLedUtils.getApiColorValues(Color.RED);

        assertThat(apiColorValues).containsExactly(100, 0, 0);

    }
}