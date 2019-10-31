package ru.sbt.mipt.oop.smarthome.alarm;

public class DeactivatedAlarm extends HomeAlarmState {

    private final String code;

    DeactivatedAlarm(HomeAlarm homeAlarm, String code) {
        this.code = code;
        this.homeAlarm = homeAlarm;
    }

    @Override
    public void activate(String code) {
        if (code.equals(this.code)) {
            homeAlarm.setState(new ActivatedAlarm(homeAlarm, code));
        }
    }

    @Override
    public void deactivate(String code) {
    }

    @Override
    public void activateDangerAlarm() {
        homeAlarm.setState(new ActivatedDangerAlarm(homeAlarm, code));
    }
}
