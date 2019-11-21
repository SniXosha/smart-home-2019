package ru.sbt.mipt.oop.smarthome.adapters.converters;

import com.coolcompany.smarthome.events.CCSensorEvent;
import ru.sbt.mipt.oop.smarthome.sensorevents.SensorEvent;

public interface CCSensorEventConverter {
    SensorEvent getSensorEvent(CCSensorEvent event);
}
