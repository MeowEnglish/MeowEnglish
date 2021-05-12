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


    private ArrayList<Book> books = new ArrayList<>();


    @Override
    public void onCreate() {
        super.onCreate();

        String fileContent = readFromFile(SAVE_FILE_PATH);
        if (!fileContent.equals(""))
        {
            Gson libraryGson = new GsonBuilder()
                    .registerTypeAdapter(Book.class, new BookSerializer())
                    .setPrettyPrinting()
                    .create();

            Type typeOfBooksArray = new TypeToken<ArrayList<Book>>() { }.getType();
            ArrayList<Book> books = libraryGson.fromJson(fileContent, typeOfBooksArray);
            if (books != null)
            {
                this.books = books;
            }
        }
    }


    public void saveData()
    {
        Gson libraryGson = new GsonBuilder()
                .registerTypeAdapter(Book.class, new BookSerializer())
                .setPrettyPrinting()
                .create();
        Type typeOfBooksArray = new TypeToken<ArrayList<Book>>() { }.getType();
        String booksJson = libraryGson.toJson(books, typeOfBooksArray);

        writeToFile(booksJson, SAVE_FILE_PATH);
    }

    public  ArrayList<Book> getBooks()
    {
        return books;
    }

    public Book getBook(Book book)
    {
        for (Book libraryBook : books)
        {
            if (libraryBook.getTitle().equals(book.getTitle()))
            {
                return libraryBook;
            }
        }

        return null;
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
}
