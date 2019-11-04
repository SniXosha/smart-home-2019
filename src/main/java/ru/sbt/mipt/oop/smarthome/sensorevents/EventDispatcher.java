package ru.sbt.mipt.oop.smarthome.sensorevents;

import ru.sbt.mipt.oop.smarthome.sensorevents.alarmevent.AlarmEventType;
import ru.sbt.mipt.oop.smarthome.sensorevents.alarmevent.AlarmSensorEvent;
import ru.sbt.mipt.oop.smarthome.sensorevents.doorevent.DoorEventType;
import ru.sbt.mipt.oop.smarthome.sensorevents.doorevent.DoorSensorEvent;
import ru.sbt.mipt.oop.smarthome.sensorevents.lightevent.LightEventType;
import ru.sbt.mipt.oop.smarthome.sensorevents.lightevent.LightSensorEvent;

public class EventDispatcher {

    public static SensorEvent getNextSensorEvent() {
        // pretend like we're getting the events from physical world, but here we're going to just generate some random events
        if (Math.random() < 0.01) return null; // null means end of event stream
        if (Math.random() < 0.45) { //door
            String objectId = "" + ((int) (10 * Math.random()));
            return new DoorSensorEvent(objectId, (Math.random() < 0.5) ? DoorEventType.CLOSE : DoorEventType.OPEN);
        } else if (Math.random() < 0.90) { //light
            String objectId = "" + ((int) (10 * Math.random()));
            return new LightSensorEvent(objectId, (Math.random() < 0.5) ? LightEventType.OFF : LightEventType.ON);
        } else { //alarm
            String code = (Math.random() < 0.9) ? "code" : "badcode";
            return new AlarmSensorEvent((Math.random() < 0.5) ? AlarmEventType.ACTIVATE : AlarmEventType.DEACTIVATE, code);
        }
    }
}
