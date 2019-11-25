package ru.sbt.mipt.oop.smarthome.actions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.smarthome.Room;
import ru.sbt.mipt.oop.smarthome.SmartHome;
import ru.sbt.mipt.oop.smarthome.actions.common.SetAllLightsAction;
import ru.sbt.mipt.oop.smarthome.devices.Light;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SetAllLightsActionTest {
    private SmartHome smartHome;
    private Collection<Light> lights;

    @BeforeEach
    void setUp() {
        Room hall = new Room("hall");
        lights = Arrays.asList(new Light("1", true), new Light("2", false));
        for (Light light : lights) {
            hall.addActionable(light);
        }

        smartHome = new SmartHome(Arrays.asList(hall), null);
    }

    @Test
    void testTurnOnAllLights() {
        smartHome.execute(new SetAllLightsAction(true));
        for (Light light : lights) {
            assertTrue(light.isOn());
        }
    }

    @Test
    void testTurnOffAllLights() {
        smartHome.execute(new SetAllLightsAction(false));
        for (Light light : lights) {
            assertFalse(light.isOn());
        }
    }
}
