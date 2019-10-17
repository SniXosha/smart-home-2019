package ru.sbt.mipt.oop.smarthome;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Room implements Actionable, Iterable<Actionable> {
    private final Collection<Actionable> devices;
    private final String name;
    private final String type = "room";


    public Room(String name) {
        this.devices = new ArrayList<>();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addActionable(Actionable actionable) {
        devices.add(actionable);
    }


    @Override
    public void execute(Action action) {
        executeHallDoor(action);
        executeForAllChildren(action);
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public Iterator<Actionable> iterator() {
        return devices.iterator();
    }

    private void executeForAllChildren(Action action) {
        for (Actionable device : devices) {
            device.execute(action);
        }
    }

    private boolean isHallDoorAction(Action action) {
        if (!name.equals("hall")) {
            return false;
        }
        return action.getTargetClass().equals(Room.class) &&
                action.getCommand().equals("halldoor") &&
                action.getAnswer().equals(HallDoorEventProcessor.roomInitAnswer);
    }

    private void executeHallDoor(Action action) {
        if (isHallDoorAction(action)) {
            Action idAction = new StaticAction(Door.class, action.getId(), "id");
            idAction.setAnswer(HallDoorEventProcessor.deviceInitAnswer);
            executeForAllChildren(idAction);
            if (idAction.getAnswer().equals(HallDoorEventProcessor.deviceInitAnswer)) {
                action.setAnswer("false");
            } else {
                action.setAnswer("true");
            }
        }
    }
}
