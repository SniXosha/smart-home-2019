package ru.sbt.mipt.oop.smarthome.actions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.smarthome.SmartHome;
import ru.sbt.mipt.oop.smarthome.actions.common.ActivateDangerAlarmAction;
import ru.sbt.mipt.oop.smarthome.alarm.ActivatedDangerAlarm;
import ru.sbt.mipt.oop.smarthome.alarm.HomeAlarm;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ActivateDangerAlarmActionTest {

    private final String code = "123";
    private SmartHome smartHome;

    @BeforeEach
    void setUp() {
        HomeAlarm homeAlarm = new HomeAlarm(code);
        smartHome = new SmartHome(new ArrayList<>(), homeAlarm);
    }

    @Test
    void testActivate() {
        smartHome.execute(new ActivateDangerAlarmAction());
        assertTrue(smartHome.getHomeAlarm().getState() instanceof ActivatedDangerAlarm);
    }
}
