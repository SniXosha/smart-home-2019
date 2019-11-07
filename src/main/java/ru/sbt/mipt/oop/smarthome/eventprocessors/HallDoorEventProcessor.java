package ru.sbt.mipt.oop.smarthome.eventprocessors;

import ru.sbt.mipt.oop.smarthome.SmartHome;
import ru.sbt.mipt.oop.smarthome.actions.Action;
import ru.sbt.mipt.oop.smarthome.commands.CommandSender;
import ru.sbt.mipt.oop.smarthome.commands.CommandType;
import ru.sbt.mipt.oop.smarthome.commands.SensorCommand;
import ru.sbt.mipt.oop.smarthome.devices.Door;
import ru.sbt.mipt.oop.smarthome.devices.Light;
import ru.sbt.mipt.oop.smarthome.sensorevents.SensorEvent;
import ru.sbt.mipt.oop.smarthome.sensorevents.SensorEventType;

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
    public void processEvent(SensorEvent event) {
        if (event.getType() != SensorEventType.DOOR_CLOSED) {
            return;
        }
        IsHallDoor isHallDoor = new IsHallDoor(event.getObjectId());
        smartHome.execute(isHallDoor);
        if (!isHallDoor.check()) {
            return;
        }
        smartHome.execute(obj -> {
            if (obj instanceof Door) {
                Door door = (Door) obj;
                if (door.getId().equals(event.getObjectId())) {
                    door.setOpen(false);
                }
            } else if (obj instanceof Light) {
                ((Light) obj).setOn(false);
                CommandSender.sendCommand(new SensorCommand(CommandType.LIGHT_OFF, ((Light) obj).getId()));
            }
        });
    }
}
