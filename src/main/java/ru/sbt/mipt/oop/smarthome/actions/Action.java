package ru.sbt.mipt.oop.smarthome.actions;

public interface Action {

    Class<?> getTargetClass();

    String getId();

    String getCommand();

    String getAnswer();

    void setAnswer(String answer);

}
