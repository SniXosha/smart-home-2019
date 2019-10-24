package ru.sbt.mipt.oop.smarthome.sensorevents.lightevent;
import ru.sbt.mipt.oop.smarthome.sensorevents.IdSensorEvent;

public class LightSensorEvent extends IdSensorEvent {
    private final LightEventType type;

    public LightSensorEvent(String objectId, LightEventType type) {
        super(objectId);
        this.type = type;
    }
    public LightEventType getType() {
        return type;
    }
}
