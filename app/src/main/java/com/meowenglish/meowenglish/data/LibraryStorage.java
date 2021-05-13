package com.meowenglish.meowenglish.data;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class LibraryStorage extends Application implements Serializable {
    private final String SAVE_FILE_PATH = "applicationData.json";


    private StoredData storedData = new StoredData();


    @Override
    public void onCreate() {
        super.onCreate();

        loadData();
    }


    public void loadData()
    {
        String fileContent = readFromFile(SAVE_FILE_PATH);
        //fileContent = "";
        if (!fileContent.equals(""))
        {
            Gson libraryGson = new GsonBuilder()
                    .registerTypeAdapter(StoredData.class, new StoredDataSerializer())
                    .setPrettyPrinting()
                    .create();

            StoredData loadedStoredData = new StoredData();
            try
            {
                loadedStoredData = libraryGson.fromJson(fileContent, StoredData.class);
            }
            catch (Exception exception)
            {

            }
            this.storedData = loadedStoredData;
        }
    }
    public void saveData()
    {
        Gson libraryGson = new GsonBuilder()
                .registerTypeAdapter(StoredData.class, new StoredDataSerializer())
                .setPrettyPrinting()
                .create();
        String storedDataJson = libraryGson.toJson(storedData, StoredData.class);

        writeToFile(storedDataJson, SAVE_FILE_PATH);
    }

    private void writeToFile(String data, String filePath) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput(filePath, Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e) {

        }
    }

    private String readFromFile(String filePath) {
        String readFile = "";

        try {
            InputStream inputStream  = getApplicationContext().openFileInput(filePath);

            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String receiveString = "";
            StringBuilder stringBuilder = new StringBuilder();

            while ( (receiveString = bufferedReader.readLine()) != null ) {
                stringBuilder.append(receiveString);
            }

            inputStream.close();
            readFile = stringBuilder.toString();
        }
        catch (FileNotFoundException e) {

        } catch (IOException e) {

        }

        return readFile;
    }
    public void addWordToBook(Book book, Word word)
    {
        storedData.getBook(book).addWord(word);
    }
    public void removeWordFromKnown(Word word)
    {
        storedData.words.remove(word);
    }
    public void addWordToKnown(Word word)
    {
        storedData.words.add(word);
    }
    public void removeWordFromBooks(String word)
    {
        for (Book book : storedData.books)
        {
            book.removeWord(word);
        }
    }

    public StoredData getData()
    {
        return storedData;
    }
}
