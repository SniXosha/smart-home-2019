package ru.sbt.mipt.oop.smarthome.eventprocessors;

public interface EventProcessor {
    void processEvent(Object event);

    boolean isCorrectEvent(Object event);
}
