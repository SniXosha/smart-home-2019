package ru.sbt.mipt.oop.smarthome.alarm;

interface Alarm {

    void activate(String code);

    void deactivate(String code);

    void activateDangerAlarm();
}
