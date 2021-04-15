package com.meowenglish.meowenglish.data;

import java.util.TreeMap;

public class Book
{
    private String title;
    private byte[] coverImage;

    private TreeMap<String, Integer> wordFrequencies;


    public Book(String title, byte[] coverImage)
    {
        this.title = title;
        this.coverImage = coverImage;
    }


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
