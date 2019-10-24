package ru.sbt.mipt.oop.smarthome.eventprocessors;

import ru.sbt.mipt.oop.smarthome.SmartHome;
import ru.sbt.mipt.oop.smarthome.actions.Action;
import ru.sbt.mipt.oop.smarthome.devices.Door;
import ru.sbt.mipt.oop.smarthome.devices.Light;
import ru.sbt.mipt.oop.smarthome.sensorevents.doorevent.DoorSensorEvent;

import static ru.sbt.mipt.oop.smarthome.sensorevents.doorevent.DoorEventType.CLOSE;

class IsHallDoor implements Action {

    private final String doorId;
    private boolean result = false;

    IsHallDoor(String doorId) {
        this.doorId = doorId;
    }

    @Override
    public void execute(Object obj) {
        if (obj instanceof Door) {
            Door door = (Door) obj;
            if (door.getId().equals(doorId) && door.getRoomName().equals("hall")) {
                result = true;
            }
        }
    }

    public boolean check() {
        return result;
    }
}

public class HallDoorEventProcessor implements EventProcessor {

    private final SmartHome smartHome;

    public HallDoorEventProcessor(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void processEvent(Object event) {
        if (!isCorrectEvent(event)) return;
        DoorSensorEvent doorSensorEvent = (DoorSensorEvent) event;
        IsHallDoor isHallDoor = new IsHallDoor(doorSensorEvent.getObjectId());
        smartHome.execute(isHallDoor);
        if (!isHallDoor.check()) {
            return;
        }
        smartHome.execute(obj -> {
            if (obj instanceof Door) {
                Door door = (Door) obj;
                if (door.getId().equals(doorSensorEvent.getObjectId())) {
                    door.setOpen(false);
                }
            } else if (obj instanceof Light) {
                ((Light) obj).setOn(false);
            }
        });
    }

    @Override
    public boolean isCorrectEvent(Object event) {
        return event instanceof DoorSensorEvent && ((DoorSensorEvent) event).getType() == CLOSE;
    }
}
