package ru.sbt.mipt.oop.smarthome;

import ru.sbt.mipt.oop.smarthome.eventprocessors.EventProcessor;
import ru.sbt.mipt.oop.smarthome.sensorevents.EventDispatcher;
import ru.sbt.mipt.oop.smarthome.sensorevents.SensorEvent;

public class HomeRunner {

    private final EventProcessor homeProcessor;

    public HomeRunner(EventProcessor homeProcessor) {
        this.homeProcessor = homeProcessor;
    }

    public void run() {
        // начинаем цикл обработки событий
        SensorEvent event = EventDispatcher.getNextSensorEvent();
        while (event != null) {
            System.out.println("Got event: " + event);
            homeProcessor.processEvent(event);
            event = EventDispatcher.getNextSensorEvent();
        }
    }
}
