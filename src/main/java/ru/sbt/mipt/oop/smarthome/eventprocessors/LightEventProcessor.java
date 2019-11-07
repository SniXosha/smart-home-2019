package ru.sbt.mipt.oop.smarthome.eventprocessors;

import ru.sbt.mipt.oop.smarthome.SmartHome;
import ru.sbt.mipt.oop.smarthome.actions.Action;
import ru.sbt.mipt.oop.smarthome.devices.Light;
import ru.sbt.mipt.oop.smarthome.sensorevents.SensorEvent;
import ru.sbt.mipt.oop.smarthome.sensorevents.SensorEventType;

import static ru.sbt.mipt.oop.smarthome.sensorevents.SensorEventType.LIGHT_OFF;
import static ru.sbt.mipt.oop.smarthome.sensorevents.SensorEventType.LIGHT_ON;

public class LightEventProcessor implements EventProcessor {

    private final SmartHome smartHome;

    public LightEventProcessor(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    private Action parseEvent(SensorEvent event) {
        SensorEventType type = event.getType();
        if (type != LIGHT_ON && type != LIGHT_OFF) {
            return null;
        }
        boolean newLightState = (type == LIGHT_ON);

        return obj -> {
            if (!(obj instanceof Light)) {
                return;
            }
            Light light = (Light) obj;
            if (light.getId().equals(event.getObjectId())) {
                light.setOn(newLightState);
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
