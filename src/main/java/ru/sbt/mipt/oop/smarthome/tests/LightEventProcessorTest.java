package ru.sbt.mipt.oop.smarthome.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import ru.sbt.mipt.oop.smarthome.*;
import ru.sbt.mipt.oop.smarthome.actions.Actionable;
import ru.sbt.mipt.oop.smarthome.devices.Door;
import ru.sbt.mipt.oop.smarthome.devices.Light;
import ru.sbt.mipt.oop.smarthome.eventprocessors.LightEventProcessor;
import ru.sbt.mipt.oop.smarthome.sensorevents.SensorEvent;
import ru.sbt.mipt.oop.smarthome.sensorevents.SensorEventType;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;


class LightEventProcessorTest {

    private SmartHome smartHome;
    private LightEventProcessor eventProcessor;
    private Light kitchenLight;
    private Light bathroomLight;

    @BeforeEach
    void setUp() throws IOException {
        Room kitchen = new Room("kitchen");
        Room bathroom = new Room("bathroom");

        kitchenLight = new Light("2", true);
        kitchen.addActionable(kitchenLight);
        kitchen.addActionable(new Door("1", true));


        bathroomLight = new Light("4", true);
        bathroom.addActionable(bathroomLight);
        bathroom.addActionable(new Door("3", true));


        List<Actionable> rooms = Arrays.asList(kitchen, bathroom);
        smartHome = new SmartHome(rooms);

        eventProcessor = new LightEventProcessor();
        eventProcessor.setSmartHome(smartHome);
    }

    @Test
    void testOff() {
        eventProcessor.processEvent(new SensorEvent(SensorEventType.LIGHT_OFF, kitchenLight.getId()));
        assertFalse(kitchenLight.isOn());
        assertTrue(bathroomLight.isOn());
    }

    @Test
    void testBadId() {
        eventProcessor.processEvent(new SensorEvent(SensorEventType.LIGHT_OFF, "badId"));
        assertTrue(kitchenLight.isOn());
        assertTrue(bathroomLight.isOn());
    }
}