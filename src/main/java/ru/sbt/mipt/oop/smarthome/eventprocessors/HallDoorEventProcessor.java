package ru.sbt.mipt.oop.smarthome.eventprocessors;

import org.springframework.stereotype.Component;
import ru.sbt.mipt.oop.smarthome.SmartHome;
import ru.sbt.mipt.oop.smarthome.actions.common.IsHallDoorAction;
import ru.sbt.mipt.oop.smarthome.commands.CommandSender;
import ru.sbt.mipt.oop.smarthome.commands.CommandType;
import ru.sbt.mipt.oop.smarthome.commands.SensorCommand;
import ru.sbt.mipt.oop.smarthome.devices.Door;
import ru.sbt.mipt.oop.smarthome.devices.Light;
import ru.sbt.mipt.oop.smarthome.sensorevents.SensorEvent;
import ru.sbt.mipt.oop.smarthome.sensorevents.doorevent.DoorSensorEvent;

import static ru.sbt.mipt.oop.smarthome.sensorevents.doorevent.DoorEventType.CLOSE;

@Component
public class HallDoorEventProcessor implements EventProcessor {

    private final SmartHome smartHome;

    public HallDoorEventProcessor(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void processEvent(SensorEvent event) {
        if (!isCorrectEvent(event)) return;
        DoorSensorEvent doorSensorEvent = (DoorSensorEvent) event;
        IsHallDoorAction isHallDoor = new IsHallDoorAction(doorSensorEvent.getObjectId());
        smartHome.execute(isHallDoor);
        if (!isHallDoor.check()) {
            return;
        }
        closeHallDoorAndTurnOffAllLights(doorSensorEvent);
    }

    private void closeHallDoorAndTurnOffAllLights(DoorSensorEvent doorSensorEvent) {
        smartHome.execute(obj -> {
            if (obj instanceof Door) {
                Door door = (Door) obj;
                if (door.getId().equals(doorSensorEvent.getObjectId())) {
                    door.setOpen(false);
                }
            } else if (obj instanceof Light) {
                ((Light) obj).setOn(false);
                CommandSender.sendCommand(new SensorCommand(CommandType.LIGHT_OFF, ((Light) obj).getId()));
            }
        });
    }

    private boolean isCorrectEvent(Object event) {
        return event instanceof DoorSensorEvent && ((DoorSensorEvent) event).getType() == CLOSE;
    }
}
