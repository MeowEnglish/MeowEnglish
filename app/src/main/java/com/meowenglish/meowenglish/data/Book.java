package com.meowenglish.meowenglish.data;

public class Book
{
    private String title;
    private byte[] coverImage;


    public Book(String title, byte[] coverImage)
    {
        this.title = title;
        this.coverImage = coverImage;
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
}
