package de.larsgrefer.logi.sampler;

import de.larsgrefer.logi.LogiLedHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.awt.*;

@Configuration
public class Config {

    @Bean
    public LogiLedHandler logiLedHandler() {
        return new LogiLedHandler("ScreenSampler");
    }

    @Bean
    public Robot robot() throws AWTException {
        return new Robot();
    }
}
