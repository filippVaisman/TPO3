package application.Json;

import com.google.gson.*;
public class JsonSerializer {

    JsonParser parser;
    JsonObject root;

    public JsonSerializer(String jsonString){
        parser = new JsonParser();
        JsonElement tmp = parser.parse(jsonString);
        root = tmp.getAsJsonObject();
    }

    public String getElement(String key){
        return root.get(key).getAsString();
    }


}
