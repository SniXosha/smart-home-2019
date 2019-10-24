package ru.sbt.mipt.oop.smarthome.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import ru.sbt.mipt.oop.smarthome.*;
import ru.sbt.mipt.oop.smarthome.actions.Actionable;
import ru.sbt.mipt.oop.smarthome.devices.Door;
import ru.sbt.mipt.oop.smarthome.devices.Light;
import ru.sbt.mipt.oop.smarthome.eventprocessors.HallDoorEventProcessor;
import ru.sbt.mipt.oop.smarthome.sensorevents.SensorEvent;
import ru.sbt.mipt.oop.smarthome.sensorevents.SensorEventType;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;


class HallDoorEventProcessorTest {

    private HallDoorEventProcessor eventProcessor;
    private Collection<Light> lights;
    private Door hallDoor;
    private Door bathroomDoor;

    @BeforeEach
    void setUp() {
        Room hall = new Room("hall");
        Room bathroom = new Room("bathroom");

        hallDoor = new Door("2", true, hall.getName());
        Light hallLight = new Light("1", true);
        hall.addActionable(hallDoor);
        hall.addActionable(hallLight);


        bathroomDoor = new Door("3", true, bathroom.getName());
        Light bathroomLight = new Light("4", true);
        bathroom.addActionable(bathroomDoor);
        bathroom.addActionable(bathroomLight);

        lights = Arrays.asList(hallLight, bathroomLight);

        List<Actionable> rooms = Arrays.asList(hall, bathroom);
        SmartHome smartHome = new SmartHome(rooms);

        eventProcessor = new HallDoorEventProcessor(smartHome);
    }

    @Test
    void testCloseHallDoor() {
        eventProcessor.processEvent(new SensorEvent(SensorEventType.DOOR_CLOSED, hallDoor.getId()));
        assertFalse(hallDoor.isOpen());
        for (Light light : lights) {
            assertFalse(light.isOn());
        }
    }

    @Test
    void testBadId() {
        eventProcessor.processEvent(new SensorEvent(SensorEventType.DOOR_CLOSED, "badId"));
        assertTrue(hallDoor.isOpen());
        for (Light light : lights) {
            assertTrue(light.isOn());
        }
    }

    @Test
    void testCloseNotHallDoor() {
        eventProcessor.processEvent(new SensorEvent(SensorEventType.DOOR_CLOSED, bathroomDoor.getId()));
        assertTrue(bathroomDoor.isOpen());
        for (Light light : lights) {
            assertTrue(light.isOn());
        }
    }

    @Test
    void testBadEventType() {
        hallDoor.setOpen(false);
        eventProcessor.processEvent(new SensorEvent(SensorEventType.DOOR_OPEN, hallDoor.getId()));
        assertFalse(hallDoor.isOpen());
        for (Light light : lights) {
            assertTrue(light.isOn());
        }
    }
}