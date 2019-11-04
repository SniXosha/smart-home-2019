package ru.sbt.mipt.oop.smarthome.adapters;

import com.coolcompany.smarthome.events.CCSensorEvent;
import ru.sbt.mipt.oop.smarthome.sensorevents.SensorEvent;
import ru.sbt.mipt.oop.smarthome.sensorevents.doorevent.DoorEventType;
import ru.sbt.mipt.oop.smarthome.sensorevents.doorevent.DoorSensorEvent;
import ru.sbt.mipt.oop.smarthome.sensorevents.lightevent.LightEventType;
import ru.sbt.mipt.oop.smarthome.sensorevents.lightevent.LightSensorEvent;

public class CCSensorEventAdapter {
    public static SensorEvent getSensorEvent(CCSensorEvent event) {
        switch (event.getEventType()) {
            case "LightIsOn":
                return new LightSensorEvent(event.getObjectId(), LightEventType.ON);
            case "LightIsOff":
                return new LightSensorEvent(event.getObjectId(), LightEventType.OFF);
            case "DoorIsOpen":
                return new DoorSensorEvent(event.getObjectId(), DoorEventType.OPEN);
            case "DoorIsClosed":
                return new DoorSensorEvent(event.getObjectId(), DoorEventType.CLOSE);
            default:
                return null;
        }
    }
}
