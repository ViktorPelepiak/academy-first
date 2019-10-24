package adapter;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalTime;

public class LocalTimeJSONAdapter implements JsonSerializer<LocalTime>, JsonDeserializer<LocalTime> {
    @Override
    public JsonElement serialize(LocalTime src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject res = new JsonObject();
        res.addProperty("hour", src.getHour());
        res.addProperty("minute", src.getMinute());
        return res;
    }

    @Override
    public LocalTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject o = json.getAsJsonObject();
        return LocalTime.of(o.getAsJsonObject("hour").getAsInt(),o.getAsJsonObject("minute").getAsInt());
    }
}
