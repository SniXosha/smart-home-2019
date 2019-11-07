package ru.sbt.mipt.oop.smarthome.sensorevents.doorevent;

import ru.sbt.mipt.oop.smarthome.sensorevents.IdSensorEvent;

public class DoorSensorEvent extends IdSensorEvent {
    private final DoorEventType type;

    public DoorSensorEvent(String objectId, DoorEventType type) {
        super(objectId);
        this.type = type;
    }

    public DoorEventType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "DoorSensorEvent " + type.toString() + ", id " + getObjectId();
    }
}
