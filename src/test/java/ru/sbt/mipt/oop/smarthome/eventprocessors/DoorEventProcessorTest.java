package ru.sbt.mipt.oop.smarthome.eventprocessors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import ru.sbt.mipt.oop.smarthome.*;
import ru.sbt.mipt.oop.smarthome.actions.Actionable;
import ru.sbt.mipt.oop.smarthome.alarm.HomeAlarm;
import ru.sbt.mipt.oop.smarthome.devices.Door;
import ru.sbt.mipt.oop.smarthome.devices.Light;
import ru.sbt.mipt.oop.smarthome.eventprocessors.DoorEventProcessor;
import ru.sbt.mipt.oop.smarthome.sensorevents.doorevent.DoorEventType;
import ru.sbt.mipt.oop.smarthome.sensorevents.doorevent.DoorSensorEvent;
import ru.sbt.mipt.oop.smarthome.sensorevents.lightevent.LightEventType;
import ru.sbt.mipt.oop.smarthome.sensorevents.lightevent.LightSensorEvent;

import java.util.Arrays;
import java.util.List;


class DoorEventProcessorTest {

    private DoorEventProcessor eventProcessor;
    private Door kitchenDoor;
    private Door bathroomDoor;

    @BeforeEach
    void setUp() {
        Room kitchen = new Room("kitchen");
        Room bathroom = new Room("bathroom");

        kitchenDoor = new Door("2", true, kitchen.getName());
        kitchen.addActionable(kitchenDoor);
        kitchen.addActionable(new Light("1", true));


        bathroomDoor = new Door("4", true, bathroom.getName());
        bathroom.addActionable(bathroomDoor);
        bathroom.addActionable(new Light("3", true));


        List<Actionable> rooms = Arrays.asList(kitchen, bathroom);
        SmartHome smartHome = new SmartHome(rooms, new HomeAlarm("code"));

        eventProcessor = new DoorEventProcessor(smartHome);
    }

    @Test
    void testClose() {
        eventProcessor.processEvent(new DoorSensorEvent(kitchenDoor.getId(), DoorEventType.CLOSE));
        assertFalse(kitchenDoor.isOpen());
        assertTrue(bathroomDoor.isOpen());
    }

    @Test
    void testCloseAndOpen() {
        eventProcessor.processEvent(new DoorSensorEvent(kitchenDoor.getId(), DoorEventType.CLOSE));
        assertFalse(kitchenDoor.isOpen());
        assertTrue(bathroomDoor.isOpen());

        eventProcessor.processEvent(new DoorSensorEvent(kitchenDoor.getId(), DoorEventType.OPEN));
        assertTrue(kitchenDoor.isOpen());
        assertTrue(bathroomDoor.isOpen());
    }

    @Test
    void testBadId() {
        eventProcessor.processEvent(new DoorSensorEvent("badId", DoorEventType.CLOSE));
        assertTrue(kitchenDoor.isOpen());
        assertTrue(bathroomDoor.isOpen());
    }

    @Test
    void testBadEventType() {
        eventProcessor.processEvent(new LightSensorEvent(kitchenDoor.getId(), LightEventType.ON));
        assertTrue(kitchenDoor.isOpen());
        assertTrue(bathroomDoor.isOpen());
    }
}