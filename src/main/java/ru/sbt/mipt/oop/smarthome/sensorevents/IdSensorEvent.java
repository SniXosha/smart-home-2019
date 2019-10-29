package ru.sbt.mipt.oop.smarthome.sensorevents;

public class IdSensorEvent {
    private final String objectId;

    protected IdSensorEvent(String objectId) {
        this.objectId = objectId;
    }

    public String getObjectId() {
        return objectId;
    }
}
