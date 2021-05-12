package com.meowenglish.meowenglish.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.TreeMap;
import java.util.ArrayList;

public class Book implements Parcelable, Serializable
{
    public final static String PARCELABLE_EXTRA_NAME = "BOOK";
    /*Название книги*/
    private String title;

    /*Название книги*/
    private String filePath;

    /*Обложка книги*/
    private byte[] coverImage;

    private TreeMap<String, Integer> wordFrequencies = new TreeMap<>();

    /*Состояние книги: например: 1 - открыта
     *   2 - находится в изучении
     *   3 - Изучена*/
    private byte State;

    /*Время начало изучения книги*/
    private long DateAdded;

    /*Время посленего взаимодеймвтия с книгой*/
    private long DateOfLastStudy;


    public Book(String title, byte[] coverImage, String filePath)
    {
        this.title = title;
        this.coverImage = coverImage;
        this.filePath = filePath;
    }
    public Book(String title, byte[] coverImage, String filePath, TreeMap<String, Integer> wordFrequencies)
    {
        this(title, coverImage, filePath);

        this.wordFrequencies = wordFrequencies;
    }


    protected Book(Parcel in) {
        title = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
    }
    @Override
    public boolean equals(Object otherBook) {

        // If the object is compared with itself then return true
        if (otherBook == this) {
            return true;
        }

        /* Check if o is an instance of Complex or not
          "null instanceof [type]" also returns false */
        if (!(otherBook instanceof Book)) {
            return false;
        }

        // Compare the data members and return accordingly
        return this.title.equals(((Book) otherBook).title);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    public void AddWordFrequencies(TreeMap<String, Integer> wordFrequencies) {
        this.wordFrequencies = wordFrequencies;
    }


    /*Здесь можно создать конструктор, в который будет передаваться путь к файлу*/

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public byte[] getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(byte[] coverImage) {
        this.coverImage = coverImage;
    }

    public TreeMap<String, Integer> getWordFrequencies() {
        return wordFrequencies;
    }

    public byte getState() {
        return State;
    }

    public void setState(byte state) {
        State = state;
    }

    public long getDateAdded() {
        return DateAdded;
    }

    public void setDateAdded(long dateAdded) {
        DateAdded = dateAdded;
    }

    public long getDateOfLastStudy() {
        return DateOfLastStudy;
    }

    public void setDateOfLastStudy(long dateOfLastStudy) {
        DateOfLastStudy = dateOfLastStudy;
    }
}
