package de.larsgrefer.logi.sampler;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

@Data
@ConfigurationProperties
public class SamplerProperties {

    private int screen = 1;

    private Duration rate = Duration.ofMillis(10);
}
