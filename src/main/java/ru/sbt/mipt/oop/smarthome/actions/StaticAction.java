package ru.sbt.mipt.oop.smarthome.actions;

public class StaticAction implements Action {

    private final Class<?> targetClass;
    private final String id;
    private final String command;
    private String answer;

    public StaticAction(Class<?> targetClass, String id, String command) {
        this.targetClass = targetClass;
        this.id = id;
        this.command = command;
        this.answer = "";
    }

    @Override
    public Class<?> getTargetClass() {
        return targetClass;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getCommand() {
        return command;
    }

    @Override
    public String getAnswer() {
        return answer;
    }

    @Override
    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
