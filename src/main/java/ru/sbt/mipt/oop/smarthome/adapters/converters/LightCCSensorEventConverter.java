package ru.sbt.mipt.oop.smarthome.adapters.converters;

import com.coolcompany.smarthome.events.CCSensorEvent;
import org.springframework.stereotype.Component;
import ru.sbt.mipt.oop.smarthome.sensorevents.SensorEvent;
import ru.sbt.mipt.oop.smarthome.sensorevents.lightevent.LightEventType;
import ru.sbt.mipt.oop.smarthome.sensorevents.lightevent.LightSensorEvent;

import static ru.sbt.mipt.oop.smarthome.sensorevents.lightevent.LightEventType.OFF;
import static ru.sbt.mipt.oop.smarthome.sensorevents.lightevent.LightEventType.ON;

@Component
public class LightCCSensorEventConverter implements CCSensorEventConverter {
    @Override
    public SensorEvent getSensorEvent(CCSensorEvent event) {
        LightEventType type = null;
        if (event.getEventType().equals("LightIsOn")) {
            type = ON;
        }
        if (event.getEventType().equals("LightIsOff")) {
            type = OFF;
        }
        if (type != null) {
            return new LightSensorEvent(event.getObjectId(), type);
        } else {
            return null;
        }
    }
}
