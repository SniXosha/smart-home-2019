package ru.sbt.mipt.oop.smarthome;

import ru.sbt.mipt.oop.smarthome.sensorevents.EventDispatcher;

public class HomeRunner {

    private final HomeProcessor homeProcessor;

    public HomeRunner(HomeProcessor homeProcessor) {
        this.homeProcessor = homeProcessor;
    }

    public void run() {
        // начинаем цикл обработки событий
        Object event = EventDispatcher.getNextSensorEvent();
        while (event != null) {
            System.out.println("Got event: " + event);
            homeProcessor.processEvent(event);
            event = EventDispatcher.getNextSensorEvent();
        }
    }
}
