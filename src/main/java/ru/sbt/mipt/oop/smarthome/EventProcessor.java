package ru.sbt.mipt.oop.smarthome;

public interface EventProcessor {
    void processEvent(SensorEvent event);

    void setSmartHome(SmartHome smartHome);
}