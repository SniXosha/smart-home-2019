package ru.sbt.mipt.oop.smarthome.adapters.converters;

import com.coolcompany.smarthome.events.CCSensorEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.sbt.mipt.oop.smarthome.sensorevents.SensorEvent;
import java.util.List;

@Component
public class BaseCCSensorEventConverter {

    @Autowired
    private List<CCSensorEventConverter> converters;

    public SensorEvent getSensorEvent(CCSensorEvent event) {
        for (CCSensorEventConverter converter : converters) {
            SensorEvent sensorEvent = converter.getSensorEvent(event);
            if (sensorEvent != null) return sensorEvent;
        }
        return null;
    }
}
