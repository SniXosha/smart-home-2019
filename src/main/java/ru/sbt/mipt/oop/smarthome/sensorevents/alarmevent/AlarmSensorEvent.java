package ru.sbt.mipt.oop.smarthome.sensorevents.alarmevent;

import ru.sbt.mipt.oop.smarthome.sensorevents.SensorEvent;

public class AlarmSensorEvent implements SensorEvent {
    private final String code;
    private final AlarmEventType type;

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

    @Override
    public String toString() {
        String s = "AlarmSensorEvent " + type.toString();
        if (code != null) {
            s += ", code " + getCode();
        }
        return s;
    }
}
