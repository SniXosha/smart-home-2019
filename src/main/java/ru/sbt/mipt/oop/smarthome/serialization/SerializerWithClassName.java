package ru.sbt.mipt.oop.smarthome.serialization;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

class SerializerWithClassName<T> implements JsonSerializer<T> {

    @Override
    public JsonElement serialize(T t, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject json = jsonSerializationContext.serialize(t).getAsJsonObject();
        json.addProperty("className", t.getClass().getName());
        return json;
    }
}
