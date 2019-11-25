package ru.sbt.mipt.oop.smarthome.actions.common;

import ru.sbt.mipt.oop.smarthome.actions.Action;
import ru.sbt.mipt.oop.smarthome.devices.Light;

public class SetAllLightsAction implements Action {

    private final boolean state;

    public SetAllLightsAction(boolean state) {
        this.state = state;
    }

    @Override
    public void execute(Object obj) {
        if (obj instanceof Light) {
            ((Light) obj).setOn(state);
        }
    }
}
