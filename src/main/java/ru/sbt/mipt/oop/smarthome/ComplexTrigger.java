package ru.sbt.mipt.oop.smarthome;

public interface ComplexTrigger {
    String getType();
    void setSmartHome(SmartHome smartHome);
    void processEvent(SensorEvent event);
}
