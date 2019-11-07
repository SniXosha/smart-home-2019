package ru.sbt.mipt.oop.smarthome.actions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.smarthome.Room;
import ru.sbt.mipt.oop.smarthome.SmartHome;
import ru.sbt.mipt.oop.smarthome.actions.common.CloseHallDoorAction;
import ru.sbt.mipt.oop.smarthome.devices.Door;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CloseHallDoorActionTest {
    private SmartHome smartHome;
    private Door hallDoor;
    private Door kitchenDoor;

    @BeforeEach
    void setUp() {
        Room hall = new Room("hall");
        Room kitchen = new Room("kitchen");
        hallDoor = new Door("1", true, hall.getName());
        kitchenDoor = new Door("2", true, kitchen.getName());
        hall.addActionable(hallDoor);
        kitchen.addActionable(kitchenDoor);

        smartHome = new SmartHome(Arrays.asList(hall, kitchen), null);
    }

    @Test
    void testCloseHallDoor() {
        smartHome.execute(new CloseHallDoorAction());
        assertFalse(hallDoor.isOpen());
        assertTrue(kitchenDoor.isOpen());
    }
}
