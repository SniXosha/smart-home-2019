package ru.sbt.mipt.oop.smarthome.eventprocessors;

import ru.sbt.mipt.oop.smarthome.*;
import ru.sbt.mipt.oop.smarthome.actions.Action;
import ru.sbt.mipt.oop.smarthome.actions.StaticAction;
import ru.sbt.mipt.oop.smarthome.devices.Door;
import ru.sbt.mipt.oop.smarthome.sensorevents.SensorEvent;
import ru.sbt.mipt.oop.smarthome.sensorevents.SensorEventType;

public class DoorEventProcessor implements EventProcessor {

    private SmartHome smartHome;

    public DoorEventProcessor(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    private Action parseEvent(SensorEvent event) {
        String command = "none";
        if (event.getType() == SensorEventType.DOOR_CLOSED) {
            command = "close";
        } else if (event.getType() == SensorEventType.DOOR_OPEN) {
            command = "open";
        }
        if (command.equals("none")) {
            return null;
        }
        return new StaticAction(Door.class, event.getObjectId(), command);
    }

    @Override
    public void processEvent(SensorEvent event) {
        Action action = parseEvent(event);
        if (action != null) {
            smartHome.execute(action);
        }
    }
}
