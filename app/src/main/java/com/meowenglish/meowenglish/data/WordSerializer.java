package com.meowenglish.meowenglish.data;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class WordSerializer implements JsonSerializer<Word>, JsonDeserializer<Word> {
    @Override
    public JsonElement serialize(Word src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject obj = new JsonObject();

        obj.addProperty("text", src.getText());
        obj.addProperty("repeatCount", src.getRepeatCount());
        obj.addProperty("frequency", src.getFrequency());
        obj.addProperty("lastActivityTime", src.getLastActivityTime());
        obj.addProperty("startTime", src.getStudyStartTime());

        return obj;
    }
    @Override
    public Word deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        String text = jsonObject.get("text").getAsString();
        int repeatCount = jsonObject.get("repeatCount").getAsInt();
        int frequency = jsonObject.get("frequency").getAsInt();
        long lastActivityTime = jsonObject.get("lastActivityTime").getAsLong();
        long startTime = jsonObject.get("startTime").getAsLong();

        Word word = new Word();
        word.setText(text);
        word.setRepeatCount(repeatCount);
        word.setFrequency(frequency);
        word.setLastActivityTime(lastActivityTime);
        word.setStudyStartTime(startTime);

        return word;
    }
}
