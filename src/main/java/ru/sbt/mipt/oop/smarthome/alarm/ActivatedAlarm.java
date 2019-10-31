package ru.sbt.mipt.oop.smarthome.alarm;

public class ActivatedAlarm extends HomeAlarmState {

    private final String code;

    ActivatedAlarm(HomeAlarm homeAlarm, String code) {
        this.homeAlarm = homeAlarm;
        this.code = code;
    }

    @Override
    public void activate(String code) {
    }

    @Override
    public void deactivate(String code) {
        if (code.equals(this.code)) {
            homeAlarm.setState(new DeactivatedAlarm(homeAlarm, code));
        } else {
            homeAlarm.setState(new ActivatedDangerAlarm(homeAlarm, this.code));
        }
    }

    @Override
    public void activateDangerAlarm() {
        homeAlarm.setState(new ActivatedDangerAlarm(homeAlarm, this.code));
    }
}