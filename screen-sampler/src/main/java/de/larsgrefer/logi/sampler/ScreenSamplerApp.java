package de.larsgrefer.logi.sampler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@EnableConfigurationProperties(SamplerProperties.class)
public class ScreenSamplerApp {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(ScreenSamplerApp.class);
        springApplication.setHeadless(false);
        springApplication.run(args);
    }

}
