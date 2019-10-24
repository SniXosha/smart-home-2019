package ru.sbt.mipt.oop.smarthome;

import ru.sbt.mipt.oop.smarthome.eventprocessors.EventProcessor;
import ru.sbt.mipt.oop.smarthome.sensorevents.SensorEvent;

import java.util.List;

public class HomeProcessor {

    private final SmartHome smartHome;
    private final List<EventProcessor> processors;


    public HomeProcessor(SmartHome smartHome, List<EventProcessor> processors) {
        this.smartHome = smartHome;
        this.processors = processors;
        setupProcessors();
    }

    private void setupProcessors() {
        for (EventProcessor processor : processors) {
            processor.setSmartHome(smartHome);
        }
    }

    public void processEvent(SensorEvent event) {
        for (EventProcessor processor : processors) {
            processor.processEvent(event);
        }
    }

}
