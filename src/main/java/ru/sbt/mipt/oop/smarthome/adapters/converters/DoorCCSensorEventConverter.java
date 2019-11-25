package ru.sbt.mipt.oop.smarthome.adapters.converters;

import com.coolcompany.smarthome.events.CCSensorEvent;
import org.springframework.stereotype.Component;
import ru.sbt.mipt.oop.smarthome.sensorevents.SensorEvent;
import ru.sbt.mipt.oop.smarthome.sensorevents.doorevent.DoorEventType;
import ru.sbt.mipt.oop.smarthome.sensorevents.doorevent.DoorSensorEvent;

import static ru.sbt.mipt.oop.smarthome.sensorevents.doorevent.DoorEventType.CLOSE;
import static ru.sbt.mipt.oop.smarthome.sensorevents.doorevent.DoorEventType.OPEN;

@Component
public class DoorCCSensorEventConverter implements CCSensorEventConverter {
    @Override
    public SensorEvent getSensorEvent(CCSensorEvent event) {
        DoorEventType type = null;
        if (event.getEventType().equals("DoorIsOpen")) {
            type = OPEN;
        }
        if (event.getEventType().equals("DoorIsClosed")) {
            type = CLOSE;
        }
        if (type != null) {
            return new DoorSensorEvent(event.getObjectId(), type);
        } else {
            return null;
        }
    }
}
