package ru.sbt.mipt.oop.smarthome.serialization;

import com.google.gson.*;

import java.lang.reflect.Type;

class DeserializerWithClassName<T> implements JsonDeserializer<T> {

    @Override
    public T deserialize(JsonElement jsonElement, Type type,
                         JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        String className = jsonObject.get("className").getAsString();
        Class<?> clazz;
        try {
            clazz = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new JsonParseException("Class not found");
        }
        return jsonDeserializationContext.deserialize(jsonObject, clazz);
    }
}
