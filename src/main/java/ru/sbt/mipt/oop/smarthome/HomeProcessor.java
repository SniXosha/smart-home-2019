package ru.sbt.mipt.oop.smarthome;

import ru.sbt.mipt.oop.smarthome.eventprocessors.EventProcessor;

import java.util.List;

public class HomeProcessor implements EventProcessor{

    private final List<EventProcessor> processors;

    public HomeProcessor(List<EventProcessor> processors) {
        this.processors = processors;
    }

    @Override
    public void processEvent(Object event) {
        for (EventProcessor processor : processors) {
            processor.processEvent(event);
        }
    }

}
