package ru.sbt.mipt.oop.smarthome.alarm;

public class DeactivatedAlarm extends HomeAlarmState {

    DeactivatedAlarm(HomeAlarm homeAlarm) {
        this.homeAlarm = homeAlarm;
    }

    @Override
    public void activate(String code) {
        homeAlarm.setState(new ActivatedAlarm(homeAlarm, code));
    }

    @Override
    public void deactivate(String code) {
    }

    @Override
    public void activateDangerAlarm() {
    }
}
