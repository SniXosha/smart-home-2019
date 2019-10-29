package ru.sbt.mipt.oop.smarthome.alarm;

public class ActivatedDangerAlarm extends HomeAlarmState {

    private final String code;

    ActivatedDangerAlarm(HomeAlarm homeAlarm, String code) {
        this.homeAlarm = homeAlarm;
        this.code = code;
    }

    @Override
    public void activate(String code) {
    }

    @Override
    public void deactivate(String code) {
        if (code.equals(this.code)) {
            homeAlarm.setState(new DeactivatedAlarm(homeAlarm));
        }
    }

    @Override
    public void activateDangerAlarm() {
    }
}

