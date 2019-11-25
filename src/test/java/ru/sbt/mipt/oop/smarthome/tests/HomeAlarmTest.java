package ru.sbt.mipt.oop.smarthome.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.smarthome.alarm.ActivatedAlarm;
import ru.sbt.mipt.oop.smarthome.alarm.ActivatedDangerAlarm;
import ru.sbt.mipt.oop.smarthome.alarm.DeactivatedAlarm;
import ru.sbt.mipt.oop.smarthome.alarm.HomeAlarm;

import static org.junit.jupiter.api.Assertions.*;

class HomeAlarmTest {

    private HomeAlarm homeAlarm;
    private final String code = "123";

    @BeforeEach
    void setUp() {
        homeAlarm = new HomeAlarm(code);
    }

    @Test
    void testInitialStateIsDeactivated() {
        assertTrue(homeAlarm.getState() instanceof DeactivatedAlarm);
    }

    @Test
    void testActivation() {
        homeAlarm.activate(code);
        assertTrue(homeAlarm.getState() instanceof ActivatedAlarm);
    }

    @Test
    void testSuccessfulDeactivation() {
        homeAlarm.activate(code);
        homeAlarm.deactivate(code);
        assertTrue(homeAlarm.getState() instanceof DeactivatedAlarm);
    }

    @Test
    void testBadDeactivation() {
        homeAlarm.activate(code);
        homeAlarm.deactivate("---");
        assertTrue(homeAlarm.getState() instanceof ActivatedDangerAlarm);
    }

    @Test
    void testDangerAlarmActivationFromActivated() {
        homeAlarm.activate(code);
        homeAlarm.activateDangerAlarm();
        assertTrue(homeAlarm.getState() instanceof ActivatedDangerAlarm);
    }

    @Test
    void testDangerAlarmActivationFromDeactivated() {
        homeAlarm.activateDangerAlarm();
        assertTrue(homeAlarm.getState() instanceof ActivatedDangerAlarm);
    }

    @Test
    void TestDangerAlarmSuccessfulDeactivation() {
        homeAlarm.activateDangerAlarm();
        homeAlarm.deactivate(code);
        assertTrue(homeAlarm.getState() instanceof DeactivatedAlarm);
    }

    @Test
    void TestDangerAlarmBadDeactivation() {
        homeAlarm.activateDangerAlarm();
        homeAlarm.deactivate("---");
        assertTrue(homeAlarm.getState() instanceof ActivatedDangerAlarm);
    }
}