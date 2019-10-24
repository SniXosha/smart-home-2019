package ru.sbt.mipt.oop.smarthome.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.smarthome.devices.alarm.ActivatedAlarm;
import ru.sbt.mipt.oop.smarthome.devices.alarm.ActivatedDangerAlarm;
import ru.sbt.mipt.oop.smarthome.devices.alarm.DeactivatedAlarm;
import ru.sbt.mipt.oop.smarthome.devices.alarm.HomeAlarm;

import static org.junit.jupiter.api.Assertions.*;

class HomeAlarmTest {

    private HomeAlarm homeAlarm;

    @BeforeEach
    void setUp() {
        homeAlarm = new HomeAlarm("1");
    }

    @Test
    void testInitialStateIsDeactivated() {
        assertTrue(homeAlarm.getState() instanceof DeactivatedAlarm);
    }

    @Test
    void testActivation() {
        homeAlarm.activate("123");
        assertTrue(homeAlarm.getState() instanceof ActivatedAlarm);
    }

    @Test
    void testSuccessfulDeactivation() {
        homeAlarm.activate("123");
        homeAlarm.deactivate("123");
        assertTrue(homeAlarm.getState() instanceof DeactivatedAlarm);
    }

    @Test
    void testBadDeactivation() {
        homeAlarm.activate("123");
        homeAlarm.deactivate("321");
        assertTrue(homeAlarm.getState() instanceof ActivatedDangerAlarm);
    }

    @Test
    void testDangerAlarmActivation() {
        homeAlarm.activate("123");
        homeAlarm.activateDangerAlarm();
        assertTrue(homeAlarm.getState() instanceof ActivatedDangerAlarm);
    }

    @Test
    void TestDangerAlarmSuccessfulDeactivation() {
        homeAlarm.activate("123");
        homeAlarm.activateDangerAlarm();
        homeAlarm.deactivate("123");
        assertTrue(homeAlarm.getState() instanceof DeactivatedAlarm);
    }

    @Test
    void TestDangerAlarmBadDeactivation() {
        homeAlarm.activate("123");
        homeAlarm.activateDangerAlarm();
        homeAlarm.deactivate("321");
        assertTrue(homeAlarm.getState() instanceof ActivatedDangerAlarm);
    }
}