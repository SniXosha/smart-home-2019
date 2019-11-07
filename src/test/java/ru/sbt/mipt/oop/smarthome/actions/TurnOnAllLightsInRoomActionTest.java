package ru.sbt.mipt.oop.smarthome.actions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.smarthome.Room;
import ru.sbt.mipt.oop.smarthome.SmartHome;
import ru.sbt.mipt.oop.smarthome.actions.common.TurnOnLightsInRoomAction;
import ru.sbt.mipt.oop.smarthome.devices.Light;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TurnOnAllLightsInRoomActionTest {
    private SmartHome smartHome;
    private Collection<Light> hallLights;
    private Collection<Light> kitchenLights;

    @BeforeEach
    void setUp() {
        Room hall = new Room("hall");
        hallLights = Arrays.asList(new Light("1", false), new Light("2", false));
        for (Light light : hallLights) {
            hall.addActionable(light);
        }

        Room kitchen = new Room("kitchen");
        kitchenLights = Arrays.asList(new Light("3", false), new Light("4", false));
        for (Light light : kitchenLights) {
            kitchen.addActionable(light);
        }

        smartHome = new SmartHome(Arrays.asList(hall, kitchen), null);
    }

    @Test
    void testTurnOnAllLights() {
        smartHome.execute(new TurnOnLightsInRoomAction("hall"));
        for (Light light : hallLights) {
            assertTrue(light.isOn());
        }
        for (Light light : kitchenLights) {
            assertFalse(light.isOn());
        }
    }
}
