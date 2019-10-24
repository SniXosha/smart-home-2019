package ru.sbt.mipt.oop.smarthome.eventprocessors;

import ru.sbt.mipt.oop.smarthome.SmartHome;
import ru.sbt.mipt.oop.smarthome.actions.Action;
import ru.sbt.mipt.oop.smarthome.devices.Door;
import ru.sbt.mipt.oop.smarthome.sensorevents.SensorEvent;
import ru.sbt.mipt.oop.smarthome.sensorevents.SensorEventType;

import static ru.sbt.mipt.oop.smarthome.sensorevents.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.smarthome.sensorevents.SensorEventType.DOOR_OPEN;

public class DoorEventProcessor implements EventProcessor {

    private final SmartHome smartHome;

    public DoorEventProcessor(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    private Action parseEvent(SensorEvent event) {
        SensorEventType type = event.getType();
        if (type != DOOR_CLOSED && type != DOOR_OPEN) {
            return null;
        }
        boolean newDoorState = (type == DOOR_OPEN);

        return obj -> {
            if (!(obj instanceof Door)) {
                return;
            }
            Door door = (Door) obj;
            if (door.getId().equals(event.getObjectId())) {
                door.setOpen(newDoorState);
            }
        };
    }

    @Override
    public void processEvent(SensorEvent event) {
        Action action = parseEvent(event);
        if (action != null) {
            smartHome.execute(action);
        }
    }
}
