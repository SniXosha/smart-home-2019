package ru.sbt.mipt.oop.smarthome.serialization;

import com.google.gson.*;
import ru.sbt.mipt.oop.smarthome.alarm.HomeAlarm;
import ru.sbt.mipt.oop.smarthome.alarm.HomeAlarmState;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

class HomeAlarmDeserializer implements JsonDeserializer<HomeAlarm> {

    @Override
    public HomeAlarm deserialize(JsonElement jsonElement, Type type,
                                 JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        HomeAlarm homeAlarm = getHomeAlarm(jsonElement);
        setHomeAlarmInState(homeAlarm);
        return homeAlarm;
    }

    private void setHomeAlarmInState(HomeAlarm homeAlarm) {
        HomeAlarmState state = homeAlarm.getState();
        try {
            Field homeAlarmField = HomeAlarmState.class.getDeclaredField("homeAlarm");
            homeAlarmField.setAccessible(true);
            homeAlarmField.set(state, homeAlarm);
        } catch (NoSuchFieldException e) {
            throw new JsonParseException("Couldn't get homeAlarm field in HomeAlarmState");
        } catch (IllegalAccessException e) {
            throw new RuntimeException();
        }
    }

    private HomeAlarm getHomeAlarm(JsonElement jsonElement) {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(HomeAlarmState.class, new DeserializerWithClassName<>());
        Gson gson = builder.create();
        return gson.fromJson(jsonElement, HomeAlarm.class);
    }
}
