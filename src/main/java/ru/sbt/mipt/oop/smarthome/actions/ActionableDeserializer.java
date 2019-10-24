package ru.sbt.mipt.oop.smarthome.actions;

import com.google.gson.*;
import ru.sbt.mipt.oop.smarthome.Room;
import ru.sbt.mipt.oop.smarthome.devices.Door;
import ru.sbt.mipt.oop.smarthome.devices.Light;
import ru.sbt.mipt.oop.smarthome.devices.alarm.*;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

public class ActionableDeserializer<T extends Actionable> implements JsonDeserializer<T> {

    private HomeAlarmState deserializeHomeAlarmState(JsonElement jsonElement,
                                                     final JsonDeserializationContext deserializationContext) {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        JsonPrimitive prim = (JsonPrimitive) jsonObject.get("type");
        String typeString = prim.getAsString();
        Class<?> clazz;
        switch (typeString) {
            case "deactivated":
                clazz = (Class<?>) DeactivatedAlarm.class;
                break;
            case "activated":
                clazz = (Class<?>) ActivatedAlarm.class;
                break;
            case "activatedDanger":
                clazz = (Class<?>) ActivatedDangerAlarm.class;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + typeString);
        }
        return deserializationContext.deserialize(jsonObject, clazz);
    }

    @SuppressWarnings("unchecked")
    public T deserialize(final JsonElement jsonElement, final Type type,
                         final JsonDeserializationContext deserializationContext
    ) throws JsonParseException {

        JsonObject jsonObject = jsonElement.getAsJsonObject();
        JsonPrimitive prim = (JsonPrimitive) jsonObject.get("type");
        String typeString = prim.getAsString();
        Class<T> deviceClass = null;
        switch (typeString) {
            case "light":
                deviceClass = (Class<T>) Light.class;
                break;
            case "door":
                deviceClass = (Class<T>) Door.class;
                break;
            case "room":
                deviceClass = (Class<T>) Room.class;
                break;
            case "homealarm":
                HomeAlarmState homeAlarmState = deserializeHomeAlarmState(jsonObject.get("state"), deserializationContext);
                String id = jsonObject.get("type").getAsString();
                HomeAlarm homeAlarm = new HomeAlarm(id);

                try {
                    Field stateField = HomeAlarm.class.getDeclaredField("state");
                    stateField.setAccessible(true);
                    stateField.set(homeAlarm, homeAlarmState);
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace();
                }

                try {
                    Field homeAlarmStateField = HomeAlarmState.class.getDeclaredField("homeAlarm");
                    homeAlarmStateField.setAccessible(true);
                    homeAlarmStateField.set(homeAlarmState, homeAlarm);
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace();
                }
                return (T) homeAlarm;
        }
        return deserializationContext.deserialize(jsonObject, deviceClass);
    }
}