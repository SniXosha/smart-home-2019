package ru.sbt.mipt.oop.smarthome.devices;

import ru.sbt.mipt.oop.smarthome.actions.Action;
import ru.sbt.mipt.oop.smarthome.actions.Actionable;
import ru.sbt.mipt.oop.smarthome.eventprocessors.HallDoorEventProcessor;

public class Door implements Device, Actionable {
    private final String id;
    private boolean isOpen;
    private final String type = "door";

    public Door(String id, boolean isOpen) {
        this.isOpen = isOpen;
        this.id = id;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    @Override
    public void execute(Action action) {
        executeIdAction(action);
        executeCloseOpen(action);
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getId() {
        return id;
    }

    public boolean isOpen() {
        return isOpen;
    }

    private boolean isIdAction(Action action) {
        return action.getTargetClass().equals(Door.class) &&
                action.getCommand().equals("id") &&
                action.getAnswer().equals(HallDoorEventProcessor.deviceInitAnswer);
    }

    private void executeIdAction(Action action) {
        if (isIdAction(action) && action.getId().equals(id)) {
            action.setAnswer("true");
        }
    }

    private void executeCloseOpen(Action action) {
        if (action.getId() != null && !action.getId().equals(id)) {
            return;
        }
        if (!action.getTargetClass().equals(this.getClass())) {
            return;
        }
        if (action.getCommand().equals("open")) {
            setOpen(true);
        } else if (action.getCommand().equals("close")) {
            setOpen(false);
        }
    }
}
