package ru.sbt.mipt.oop.smarthome;

import org.springframework.stereotype.Component;
import ru.sbt.mipt.oop.smarthome.eventprocessors.EventProcessor;
import ru.sbt.mipt.oop.smarthome.sensorevents.SensorEvent;

import java.util.List;

@Component
public class HomeProcessor implements EventProcessor{

    private final List<EventProcessor> processors;

    public HomeProcessor(List<EventProcessor> processors) {
        this.processors = processors;
    }

    @Override
    public void processEvent(SensorEvent event) {
        for (EventProcessor processor : processors) {
            processor.processEvent(event);
        }
    }

}
