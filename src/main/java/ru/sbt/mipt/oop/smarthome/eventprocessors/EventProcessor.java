package ru.sbt.mipt.oop.smarthome.eventprocessors;

import ru.sbt.mipt.oop.smarthome.sensorevents.SensorEvent;
import ru.sbt.mipt.oop.smarthome.SmartHome;

public interface EventProcessor {
    void processEvent(SensorEvent event);
}
