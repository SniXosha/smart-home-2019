package ru.sbt.mipt.oop.smarthome.actions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.smarthome.SmartHome;
import ru.sbt.mipt.oop.smarthome.actions.common.ActivateAlarmAction;
import ru.sbt.mipt.oop.smarthome.alarm.ActivatedAlarm;
import ru.sbt.mipt.oop.smarthome.alarm.HomeAlarm;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ActivateAlarmActionTest {

    private final String code = "123";
    private SmartHome smartHome;

    @BeforeEach
    void setUp() {
        HomeAlarm homeAlarm = new HomeAlarm(code);
        smartHome = new SmartHome(new ArrayList<>(), homeAlarm);
    }

    @Test
    void testActivateWithCorrectCode() {
        smartHome.execute(new ActivateAlarmAction(code));
        assertTrue(smartHome.getHomeAlarm().getState() instanceof ActivatedAlarm);
    }

    @Test
    void testActivateWithWrongCode() {
        smartHome.execute(new ActivateAlarmAction("badCode"));
        assertFalse(smartHome.getHomeAlarm().getState() instanceof ActivatedAlarm);
    }
}
