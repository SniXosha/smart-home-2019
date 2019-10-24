package ru.sbt.mipt.oop.smarthome.sensorevents.alarmevent;

public class AlarmSensorEvent {
    private final String code;
    private final AlarmEventType type;

    public AlarmSensorEvent(AlarmEventType type) {
        this.code = null;
        this.type = type;
    }
    public AlarmSensorEvent(AlarmEventType type, String code) {
        this.code = code;
        this.type = type;
    }


    public String getCode() {
        return code;
    }

    public AlarmEventType getType() {
        return type;
    }
}
