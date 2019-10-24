package ru.sbt.mipt.oop;

import com.google.gson.*;
import ru.sbt.mipt.oop.smarthome.*;
import ru.sbt.mipt.oop.smarthome.actions.Actionable;
import ru.sbt.mipt.oop.smarthome.devices.Door;
import ru.sbt.mipt.oop.smarthome.devices.Light;

import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

class ActionableDeserializer<T extends Actionable> implements JsonDeserializer<T> {

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
        } else if (typeString.equals("room")) {
            deviceClass = (Class<T>) Room.class;
        }
        return deserializationContext.deserialize(jsonObject, deviceClass);
    }

}

public class SimpleHome implements HomeBuilder {

    private static String filepath = "smart-home-1.js";

    public SmartHome loadSmartHome() {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Actionable.class, new ActionableDeserializer<>());
        Gson gson = builder.create();

        String json = null;
        try {
            json = new String(Files.readAllBytes(Paths.get(filepath)));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return gson.fromJson(json, SmartHome.class);
    }

    public void dumpSmartHome() {
        Room kitchen = new Room("kitchen");
        Room bathroom = new Room("bathroom");
        Room bedroom = new Room("bedroom");
        Room hall = new Room("hall");

        kitchen.addActionable(new Light("1", false));
        kitchen.addActionable(new Light("2", true));

        bathroom.addActionable(new Light("3", true));
        bathroom.addActionable(new Door("2", false));

        bedroom.addActionable(new Light("4", false));
        bedroom.addActionable(new Light("5", false));
        bedroom.addActionable(new Light("6", false));
        bedroom.addActionable(new Door("3", true));

        hall.addActionable(new Light("7", false));
        hall.addActionable(new Light("8", false));
        hall.addActionable(new Light("9", false));
        hall.addActionable(new Door("4", false));


        List<Actionable> rooms = Arrays.asList(kitchen, bathroom, bedroom, hall);
        SmartHome smartHome = new SmartHome(rooms);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();


        String jsonString = gson.toJson(smartHome);
        System.out.println(jsonString);
        Path path = Paths.get(filepath);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        SimpleHome simpleHome = new SimpleHome();
        simpleHome.dumpSmartHome();
    }

}
