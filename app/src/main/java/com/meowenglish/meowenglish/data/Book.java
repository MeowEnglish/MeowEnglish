package com.meowenglish.meowenglish.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.TreeMap;

public class Book implements Parcelable
{
    private String title;
    private byte[] coverImage;

    private TreeMap<String, Integer> wordFrequencies;


    public Book(String title, byte[] coverImage)
    {
        this.title = title;
        this.coverImage = coverImage;
    }


    protected Book(Parcel in) {
        title = in.readString();
        coverImage = in.createByteArray();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeByteArray(coverImage);
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


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
}
