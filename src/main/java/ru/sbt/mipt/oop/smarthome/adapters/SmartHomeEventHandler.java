package ru.sbt.mipt.oop.smarthome.adapters;

import com.coolcompany.smarthome.events.CCSensorEvent;
import com.coolcompany.smarthome.events.EventHandler;
import ru.sbt.mipt.oop.smarthome.HomeProcessor;
import ru.sbt.mipt.oop.smarthome.SmartHome;
import ru.sbt.mipt.oop.smarthome.eventprocessors.*;
import ru.sbt.mipt.oop.smarthome.sensorevents.SensorEvent;
import ru.sbt.mipt.oop.smarthome.serialization.HomeBuilder;
import ru.sbt.mipt.oop.smarthome.serialization.SimpleHome;
import ru.sbt.mipt.oop.smarthome.signals.ConsoleSignalSender;
import ru.sbt.mipt.oop.smarthome.signals.SignalSender;

import java.util.Arrays;
import java.util.List;

public class SmartHomeEventHandler implements EventHandler {

    private final EventProcessor eventProcessor;

    public SmartHomeEventHandler() {
        HomeBuilder homeBuilder = new SimpleHome();
        SmartHome smartHome = homeBuilder.loadSmartHome();
        if (smartHome == null) throw new RuntimeException("Couldn't load SmartHome");
        SignalSender signalSender = new ConsoleSignalSender();

        List<EventProcessor> processors = Arrays.asList(
                new AlarmEventProccessor(smartHome),
                new DoorEventProcessor(smartHome),
                new LightEventProcessor(smartHome),
                new HallDoorEventProcessor(smartHome));

        this.eventProcessor = new TriggerAlarm(smartHome.getHomeAlarm(),
                new HomeProcessor(processors), signalSender);
    }

    @Override
    public void handleEvent(CCSensorEvent event) {
        SensorEvent sensorEvent = CCSensorEventAdapter.getSensorEvent(event);
        if (sensorEvent != null) this.eventProcessor.processEvent(sensorEvent);
    }
}
