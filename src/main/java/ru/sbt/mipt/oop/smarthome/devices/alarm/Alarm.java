package ru.sbt.mipt.oop.smarthome.devices.alarm;

public interface Alarm {

    void activate(String code);
    void deactivate(String code);
    void activateDangerAlarm();
}
