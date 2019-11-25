package ru.sbt.mipt.oop.smarthome.adapters;

import com.coolcompany.smarthome.events.CCSensorEvent;
import com.coolcompany.smarthome.events.EventHandler;
import org.springframework.stereotype.Component;
import ru.sbt.mipt.oop.smarthome.HomeProcessor;
import ru.sbt.mipt.oop.smarthome.SmartHome;
import ru.sbt.mipt.oop.smarthome.adapters.converters.BaseCCSensorEventConverter;
import ru.sbt.mipt.oop.smarthome.eventprocessors.*;
import ru.sbt.mipt.oop.smarthome.sensorevents.SensorEvent;
import ru.sbt.mipt.oop.smarthome.signals.ConsoleSignalSender;
import ru.sbt.mipt.oop.smarthome.signals.SignalSender;

import javax.annotation.PostConstruct;

@Component
public class SmartHomeEventHandler implements EventHandler {

    private EventProcessor eventProcessor;

    private final BaseCCSensorEventConverter converter;
    private final SmartHome smartHome;
    private final HomeProcessor homeProcessor;

    public SmartHomeEventHandler(BaseCCSensorEventConverter converter, SmartHome smartHome, HomeProcessor homeProcessor) {
        this.converter = converter;
        this.smartHome = smartHome;
        this.homeProcessor = homeProcessor;
    }

    @PostConstruct
    private void postConstruct() {
        SignalSender signalSender = new ConsoleSignalSender();
        eventProcessor = new TriggerAlarmDecorator(smartHome.getHomeAlarm(),
                homeProcessor, signalSender);
    }

    @Override
    public void handleEvent(CCSensorEvent event) {
        SensorEvent sensorEvent = converter.getSensorEvent(event);
        if (sensorEvent != null) this.eventProcessor.processEvent(sensorEvent);
    }
}
