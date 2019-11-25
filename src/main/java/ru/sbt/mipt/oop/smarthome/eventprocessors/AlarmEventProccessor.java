package ru.sbt.mipt.oop.smarthome.eventprocessors;

import org.springframework.stereotype.Component;
import ru.sbt.mipt.oop.smarthome.SmartHome;
import ru.sbt.mipt.oop.smarthome.alarm.HomeAlarm;
import ru.sbt.mipt.oop.smarthome.sensorevents.SensorEvent;
import ru.sbt.mipt.oop.smarthome.sensorevents.alarmevent.AlarmEventType;
import ru.sbt.mipt.oop.smarthome.sensorevents.alarmevent.AlarmSensorEvent;

import static ru.sbt.mipt.oop.smarthome.sensorevents.alarmevent.AlarmEventType.ACTIVATE;
import static ru.sbt.mipt.oop.smarthome.sensorevents.alarmevent.AlarmEventType.DEACTIVATE;

@Component
public class AlarmEventProccessor implements EventProcessor {

    private final SmartHome smartHome;

    public AlarmEventProccessor(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void processEvent(SensorEvent event) {
        if (!isCorrectEvent(event)) return;
        AlarmSensorEvent alarmSensorEvent = (AlarmSensorEvent) event;

        smartHome.execute(obj -> {
            if (!(obj instanceof HomeAlarm)) return;
            HomeAlarm homeAlarm = (HomeAlarm) obj;
            AlarmEventType type = alarmSensorEvent.getType();
            String code = alarmSensorEvent.getCode();
            if (type == ACTIVATE) {
                homeAlarm.activate(code);
            } else if (type == DEACTIVATE) {
                homeAlarm.deactivate(code);
            }
        });
    }

    private boolean isCorrectEvent(Object event) {
        return event instanceof AlarmSensorEvent;
    }
}
