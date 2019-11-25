package ru.sbt.mipt.oop.smarthome.eventprocessors;

import org.springframework.stereotype.Component;
import ru.sbt.mipt.oop.smarthome.SmartHome;
import ru.sbt.mipt.oop.smarthome.actions.Action;
import ru.sbt.mipt.oop.smarthome.devices.Light;
import ru.sbt.mipt.oop.smarthome.sensorevents.SensorEvent;
import ru.sbt.mipt.oop.smarthome.sensorevents.lightevent.LightSensorEvent;

import static ru.sbt.mipt.oop.smarthome.sensorevents.lightevent.LightEventType.ON;

@Component
public class LightEventProcessor implements EventProcessor {

    private final SmartHome smartHome;

    public LightEventProcessor(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    private Action parseEvent(Object event) {
        if (!isCorrectEvent(event)) return null;
        LightSensorEvent lightSensorEvent = (LightSensorEvent) event;
        boolean newLightState = (lightSensorEvent.getType() == ON);

        return obj -> {
            if (!(obj instanceof Light)) {
                return;
            }
            Light light = (Light) obj;
            if (light.getId().equals(lightSensorEvent.getObjectId())) {
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

    private boolean isCorrectEvent(Object event) {
        return event instanceof LightSensorEvent;
    }
}
