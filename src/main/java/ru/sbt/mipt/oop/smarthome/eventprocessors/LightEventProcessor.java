package ru.sbt.mipt.oop.smarthome.eventprocessors;

import ru.sbt.mipt.oop.smarthome.*;
import ru.sbt.mipt.oop.smarthome.actions.Action;
import ru.sbt.mipt.oop.smarthome.actions.StaticAction;
import ru.sbt.mipt.oop.smarthome.devices.Light;
import ru.sbt.mipt.oop.smarthome.sensorevents.SensorEvent;
import ru.sbt.mipt.oop.smarthome.sensorevents.SensorEventType;

public class LightEventProcessor implements EventProcessor {

    private SmartHome smartHome;

    private Action parseEvent(SensorEvent event) {
        String command = "none";
        if (event.getType() == SensorEventType.LIGHT_ON) {
            command = "on";
        } else if (event.getType() == SensorEventType.LIGHT_OFF) {
            command = "off";
        }
        if (command.equals("none")) {
            return null;
        }
        return new StaticAction(Light.class, event.getObjectId(), command);
    }

    @Override
    public void processEvent(SensorEvent event) {
        Action action = parseEvent(event);
        if (action != null) {
            smartHome.execute(action);
        }
    }

    @Override
    public void setSmartHome(SmartHome smartHome) {
        this.smartHome = smartHome;
    }
}
