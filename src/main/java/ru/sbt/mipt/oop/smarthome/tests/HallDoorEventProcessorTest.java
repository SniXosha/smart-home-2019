package ru.sbt.mipt.oop.smarthome.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import ru.sbt.mipt.oop.smarthome.*;

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

        eventProcessor = new HallDoorEventProcessor();
        eventProcessor.setSmartHome(smartHome);
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