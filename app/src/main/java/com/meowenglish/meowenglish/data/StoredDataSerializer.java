package com.meowenglish.meowenglish.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class StoredDataSerializer implements JsonSerializer<StoredData>, JsonDeserializer<StoredData> {
    @Override
    public JsonElement serialize(StoredData src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject obj = new JsonObject();

        obj.addProperty("books", saveBooks(src.books));
        obj.addProperty("words", saveWords(src.words));

        return obj;
    }
    @Override
    public StoredData deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        ArrayList<Book> books = loadBooks(jsonObject.get("books").getAsString());
        ArrayList<Word> words = loadWords(jsonObject.get("words").getAsString());

        StoredData storedData = new StoredData();
        storedData.books = books;
        storedData.words = words;

        return storedData;
    }

    public ArrayList<Book> loadBooks(String fileContent)
    {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Book.class, new BookSerializer())
                .setPrettyPrinting()
                .create();

        Type typeOfBooksArray = new TypeToken<ArrayList<Book>>() { }.getType();

        ArrayList<Book> loadedStoredData = null;
        try
        {
            loadedStoredData = gson.fromJson(fileContent, typeOfBooksArray);
        }
        catch (Exception exception)
        {

        }
        return loadedStoredData;
    }
    public ArrayList<Word> loadWords(String fileContent)
    {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Word.class, new WordSerializer())
                .setPrettyPrinting()
                .create();

        Type typeOfWordsArray = new TypeToken<ArrayList<Word>>() { }.getType();

        ArrayList<Word> loadedStoredData = null;
        try
        {
            loadedStoredData = gson.fromJson(fileContent, typeOfWordsArray);
        }
        catch (Exception exception)
        {

        }
        return loadedStoredData;
    }
    public String saveBooks(ArrayList<Book> books)
    {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Book.class, new BookSerializer())
                .setPrettyPrinting()
                .create();

        Type typeOfBooksArray = new TypeToken<ArrayList<Book>>() { }.getType();
        return gson.toJson(books, typeOfBooksArray);
    }
    public String saveWords(ArrayList<Word> words)
    {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Word.class, new WordSerializer())
                .setPrettyPrinting()
                .create();

        Type typeOfWordsArray = new TypeToken<ArrayList<Word>>() { }.getType();
        return gson.toJson(words, typeOfWordsArray);
    }
}
