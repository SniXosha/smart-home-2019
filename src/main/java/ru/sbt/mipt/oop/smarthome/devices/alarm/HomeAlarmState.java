package ru.sbt.mipt.oop.smarthome.devices.alarm;

public abstract class HomeAlarmState implements Alarm {
    transient protected HomeAlarm homeAlarm;

    abstract String getStateType();
}
