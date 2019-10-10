package ru.sbt.mipt.oop;

public interface Device {


    String getType();
    String getId();

    void processEvent(SensorEvent event);
}
