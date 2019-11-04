package ru.sbt.mipt.oop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.oop.smarthome.SmartHome;
import ru.sbt.mipt.oop.smarthome.serialization.SimpleHome;

@Configuration
public class SmartHomeConfiguration {
    @Bean
    SmartHome smartHome() {
        return (new SimpleHome()).loadSmartHome();
    }
}
