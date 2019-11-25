package ru.sbt.mipt.oop.smarthome.remotecontrol;

public enum RemoteButtonType {
    ButtonA,
    ButtonB,
    ButtonC,
    ButtonD,
    Button1,
    Button2,
    Button3,
    Button4;

    public static RemoteButtonType getButtonFromCode(String code) {
        try {
            return RemoteButtonType.valueOf("Button" + code);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public static String getCodeFromButton(RemoteButtonType button) {
        String name = button.name();
        return name.substring(6);
    }
}
