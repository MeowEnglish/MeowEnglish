package com.meowenglish.meowenglish;

import android.util.Log;
import android.util.TypedValue;
import static org.junit.Assert.*;
import com.google.gson.internal.bind.TreeTypeAdapter;
import com.meowenglish.meowenglish.data.BookSerializer;
import com.google.gson.Gson;
import com.meowenglish.meowenglish.data.Book;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;

import org.junit.Test;

import java.lang.reflect.Type;
import java.util.TreeMap;



public class BookSerializerTest {

    @Test
    public void serialize() {


    }
}
