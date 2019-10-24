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

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


class HallDoorEventProcessorTest {

    private SmartHome smartHome;
    private HallDoorEventProcessor eventProcessor;
    private Collection<Light> lights;
    private Door hallDoor;

    @BeforeEach
    void setUp() throws IOException {
        Room hall = new Room("hall");
        Room bathroom = new Room("bathroom");

        hallDoor = new Door("2", true);
        Light hallLight = new Light("1", true);
        hall.addActionable(hallDoor);
        hall.addActionable(hallLight);


        Light bathroomLight = new Light("4", true);
        bathroom.addActionable(bathroomLight);

        lights = Arrays.asList(hallLight, bathroomLight);

        List<Actionable> rooms = Arrays.asList(hall, bathroom);
        smartHome = new SmartHome(rooms);

        eventProcessor = new HallDoorEventProcessor(smartHome);
    }

    @Test
    void testOff() {
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
}