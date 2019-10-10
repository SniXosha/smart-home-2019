package ru.sbt.mipt.oop;

public interface ComplexTrigger {
    String getType();
    void setSmartHome(SmartHome smartHome);
    void processEvent(SensorEvent event);
}
