package ru.sbt.mipt.oop.smarthome.devices.alarm;

public class ActivatedDangerAlarm extends HomeAlarmState {

    private final String code;
    @SuppressWarnings("FieldCanBeLocal")
    private final String type = "activatedDanger";

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

    @Override
    public String getStateType() {
        return type;
    }
}

