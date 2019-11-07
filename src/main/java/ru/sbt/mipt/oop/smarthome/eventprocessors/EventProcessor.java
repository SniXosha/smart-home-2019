package ru.sbt.mipt.oop.smarthome.eventprocessors;

import ru.sbt.mipt.oop.smarthome.sensorevents.SensorEvent;

public interface EventProcessor {
    void processEvent(SensorEvent event);
}
