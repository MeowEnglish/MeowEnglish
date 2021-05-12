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
import com.meowenglish.meowenglish.Reader.logTableOfContents;

import java.lang.reflect.Type;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class BookSerializer implements JsonSerializer<Book>, JsonDeserializer<Book> {
    @Override
    public JsonElement serialize(Book src, Type typeOfSrc, JsonSerializationContext context) {
        Gson gson = new Gson();

        Log.i("BookSerializer", "serialize");

        JsonObject obj = new JsonObject();
        obj.addProperty("title", src.getTitle());
        obj.addProperty("wordFrequencies", gson.toJson(src.getWordFrequencies()));

        return obj;
    }
    @Override
    public Book deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();

        JsonObject jsonObject = json.getAsJsonObject();

        String title = jsonObject.get("title").getAsString();
        Type typeOfArray = new TypeToken<TreeMap<String, Integer>>() { }.getType();
        Map<String, Integer> wordFrequencies = gson.fromJson(jsonObject.get("wordFrequencies").getAsString(), typeOfArray);
        TreeMap<String, Integer> sortedWordFrequencies = new TreeMap<String, Integer>(new ValueComparator(wordFrequencies));
        sortedWordFrequencies.putAll(wordFrequencies);

        return new Book(title, new byte[0], sortedWordFrequencies);
    }


    class ValueComparator implements Comparator<String> {
        Map<String, Integer> base;

        public ValueComparator(Map<String, Integer> base) {
            this.base = base;
        }

        // Note: this comparator imposes orderings that are inconsistent with
        // equals.
        public int compare(String a, String b) {
            if (base.get(a) >= base.get(b)) {
                return -1;
            } else {
                return 1;
            } // returning 0 would merge keys
        }
    }
}
