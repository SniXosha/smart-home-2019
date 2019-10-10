package ru.sbt.mipt.oop;

import com.google.gson.*;

import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

class DeviceDeserializer<T extends Device> implements JsonDeserializer<T> {

    @SuppressWarnings("unchecked")
    public T deserialize(final JsonElement jsonElement, final Type type,
                         final JsonDeserializationContext deserializationContext
    ) throws JsonParseException {

        JsonObject jsonObject = jsonElement.getAsJsonObject();
        JsonPrimitive prim = (JsonPrimitive) jsonObject.get("type");
        String typeString = prim.getAsString();
        Class<T> deviceClass = null;
        if (typeString.equals("light")) {
            deviceClass = (Class<T>) Light.class;
        } else if (typeString.equals("door")) {
            deviceClass = (Class<T>) Door.class;
        }
        return deserializationContext.deserialize(jsonObject, deviceClass);
    }

}

class ComplexTriggerDeserializer<T extends ComplexTrigger> implements JsonDeserializer<T> {

    @SuppressWarnings("unchecked")
    public T deserialize(final JsonElement jsonElement, final Type type,
                         final JsonDeserializationContext deserializationContext
    ) throws JsonParseException {

        JsonObject jsonObject = jsonElement.getAsJsonObject();
        JsonPrimitive prim = (JsonPrimitive) jsonObject.get("type");
        String typeString = prim.getAsString();
        Class<T> deviceClass = null;
        if (typeString.equals("turnoffalllights")) {
            deviceClass = (Class<T>) TurnOffAllLights.class;
        }
        return deserializationContext.deserialize(jsonObject, deviceClass);
    }

}


public class HomeBuilder {

    private static String filepath = "smart-home-1.js";

    public static SmartHome loadSmartHome()  throws IOException {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Device.class, new DeviceDeserializer<>()).
                registerTypeAdapter(ComplexTrigger.class, new ComplexTriggerDeserializer<>());
        Gson gson = builder.create();

        String json = new String(Files.readAllBytes(Paths.get(filepath)));
        SmartHome smartHome = gson.fromJson(json, SmartHome.class);
        smartHome.setupComplexTriggers();
        return smartHome;
    }

    public static void main(String[] args) throws IOException {
        Room kitchen = new Room("kitchen");
        Room bathroom = new Room("bathroom");
        Room bedroom = new Room("bedroom");
        Room hall = new Room("hall");

        kitchen.addDevice(new Light("1", false));
        kitchen.addDevice(new Light("2", true));

        bathroom.addDevice(new Light("3", true));
        bathroom.addDevice(new Door("2", false));

        bedroom.addDevice(new Light("4", false));
        bedroom.addDevice(new Light("5", false));
        bedroom.addDevice(new Light("6", false));
        bedroom.addDevice(new Door("3", true));

        hall.addDevice(new Light("7", false));
        hall.addDevice(new Light("8", false));
        hall.addDevice(new Light("9", false));
        hall.addDevice(new Door("4", false));


        List<Room> rooms = Arrays.asList(kitchen, bathroom, bedroom, hall);
        SmartHome smartHome = new SmartHome(rooms);

        TurnOffAllLights turnOffAllLights = new TurnOffAllLights();
        smartHome.addComplexTrigger(turnOffAllLights);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();


        String jsonString = gson.toJson(smartHome);
        System.out.println(jsonString);
        Path path = Paths.get(filepath);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write(jsonString);
        }
    }

}
