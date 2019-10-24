package ru.sbt.mipt.oop.smarthome.eventprocessors;

import ru.sbt.mipt.oop.smarthome.SmartHome;
import ru.sbt.mipt.oop.smarthome.actions.Action;
import ru.sbt.mipt.oop.smarthome.devices.Door;
import ru.sbt.mipt.oop.smarthome.sensorevents.doorevent.DoorSensorEvent;
import static ru.sbt.mipt.oop.smarthome.sensorevents.doorevent.DoorEventType.OPEN;

public class DoorEventProcessor implements EventProcessor {

    private final SmartHome smartHome;

    public DoorEventProcessor(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    private Action parseEvent(Object event) {
        if (!isCorrectEvent(event)) return null;
        DoorSensorEvent doorSensorEvent = (DoorSensorEvent) event;
        boolean newDoorState = (doorSensorEvent.getType() == OPEN);

        return obj -> {
            if (!(obj instanceof Door)) {
                return;
            }
            Door door = (Door) obj;
            if (door.getId().equals(doorSensorEvent.getObjectId())) {
                door.setOpen(newDoorState);
            }
        };
    }

    @Override
    public void processEvent(Object event) {
        Action action = parseEvent(event);
        if (action != null) {
            smartHome.execute(action);
        }
    }

    @Override
    public boolean isCorrectEvent(Object event) {
        return event instanceof DoorSensorEvent;
    }
}
