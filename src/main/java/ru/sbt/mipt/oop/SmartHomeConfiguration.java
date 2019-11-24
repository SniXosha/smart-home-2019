package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.EventHandler;
import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.sbt.mipt.oop.smarthome.HomeProcessor;
import ru.sbt.mipt.oop.smarthome.SmartHome;
import ru.sbt.mipt.oop.smarthome.adapters.SmartHomeEventHandler;
import ru.sbt.mipt.oop.smarthome.adapters.converters.BaseCCSensorEventConverter;
import ru.sbt.mipt.oop.smarthome.eventprocessors.EventProcessor;
import ru.sbt.mipt.oop.smarthome.serialization.HomeBuilder;
import ru.sbt.mipt.oop.smarthome.serialization.SimpleHome;

import java.util.List;

@Configuration
@ComponentScan
@Import(RemoteControlConfiguration.class)
public class SmartHomeConfiguration {
    @SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    private List<EventProcessor> eventProcessors;

    @Bean
    SensorEventsManager sensorEventsManager() {
        SensorEventsManager sensorEventsManager = new SensorEventsManager();
        sensorEventsManager.registerEventHandler(smartHomeEventHandler());
        return sensorEventsManager;
    }

    @Bean
    EventHandler smartHomeEventHandler() {
        return new SmartHomeEventHandler(baseCCSensorEventConverter(), smartHome(), homeProcessor());
    }

    @Bean
    BaseCCSensorEventConverter baseCCSensorEventConverter() {
        return new BaseCCSensorEventConverter();
    }

    @Bean
    HomeProcessor homeProcessor() {
        return new HomeProcessor(eventProcessors);
    }

    @Bean
    SmartHome smartHome() {
        HomeBuilder homeBuilder = new SimpleHome();
        SmartHome smartHome = homeBuilder.loadSmartHome();
        if (smartHome == null) throw new RuntimeException("Couldn't load SmartHome");
        return smartHome;
    }
}