package ru.sbt.mipt.oop.smarthome.eventprocessors;

import ru.sbt.mipt.oop.smarthome.SmartHome;
import ru.sbt.mipt.oop.smarthome.actions.Action;
import ru.sbt.mipt.oop.smarthome.devices.alarm.*;
import ru.sbt.mipt.oop.smarthome.signals.SignalSender;

class GetAlarm implements Action {

    private HomeAlarm homeAlarm;

    @Override
    public void execute(Object obj) {
        if (obj instanceof HomeAlarm) {
            homeAlarm = (HomeAlarm) obj;
        }
    }

    public HomeAlarm getHomeAlarm() {
        return homeAlarm;
    }
}


public class TriggerAlarm implements EventProcessor{

    private final HomeAlarm homeAlarm;
    private final EventProcessor delegate;
    private final SignalSender signalSender;

    public TriggerAlarm(SmartHome smartHome, EventProcessor delegate, SignalSender signalSender) {
        this.delegate = delegate;
        this.signalSender = signalSender;

        GetAlarm getAlarm = new GetAlarm();
        smartHome.execute(getAlarm);
        homeAlarm = getAlarm.getHomeAlarm();
    }

    @Override
    public void processEvent(Object event) {
        if (!isCorrectEvent(event)) return;
        HomeAlarmState state = homeAlarm.getState();
        if (state instanceof DeactivatedAlarm) {
            delegate.processEvent(event);
        }
        if (state instanceof ActivatedAlarm) {
            homeAlarm.activateDangerAlarm();
        }
        if (state instanceof ActivatedAlarm || state instanceof ActivatedDangerAlarm) {
            signalSender.send("Sending sms:" + event.toString());
        }
    }

    @Override
    public boolean isCorrectEvent(Object event) {
        return delegate.isCorrectEvent(event);
    }
}
