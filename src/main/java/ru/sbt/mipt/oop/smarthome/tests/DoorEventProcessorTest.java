package ru.sbt.mipt.oop.smarthome.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import ru.sbt.mipt.oop.smarthome.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;


class DoorEventProcessorTest {

    private SmartHome smartHome;
    private DoorEventProcessor eventProcessor;
    private Door kitchenDoor;
    private Door bathroomDoor;

    @BeforeEach
    void setUp() throws IOException {
        Room kitchen = new Room("kitchen");
        Room bathroom = new Room("bathroom");

        kitchenDoor = new Door("2", true);
        kitchen.addActionable(kitchenDoor);
        kitchen.addActionable(new Light("1", true));


        bathroomDoor = new Door("4", true);
        bathroom.addActionable(bathroomDoor);
        bathroom.addActionable(new Light("3", true));


        List<Actionable> rooms = Arrays.asList(kitchen, bathroom);
        smartHome = new SmartHome(rooms);

        eventProcessor = new DoorEventProcessor();
        eventProcessor.setSmartHome(smartHome);
    }

    @Test
    void testClose() {
        eventProcessor.processEvent(new SensorEvent(SensorEventType.DOOR_CLOSED, kitchenDoor.getId()));
        assertFalse(kitchenDoor.isOpen());
        assertTrue(bathroomDoor.isOpen());
    }

    @Test
    void testBadId() {
        eventProcessor.processEvent(new SensorEvent(SensorEventType.DOOR_CLOSED, "badId"));
        assertTrue(kitchenDoor.isOpen());
        assertTrue(bathroomDoor.isOpen());
    }
}