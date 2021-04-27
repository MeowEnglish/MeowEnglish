package com.meowenglish.meowenglish.data;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class Book
{
    /*Название книги*/
    private String title;

    /*Обложка книги*/
    private byte[] coverImage;

    /*Список слов данной книги*/
    private ArrayList<Word> Words;

    /*Состояние книги: например: 1 - открыта
    *   2 - находится в изучении
    *   3 - Изучена*/
    private byte State;

    /*Время начало изучения книги*/
    private long DateAdded;

    /*Время посленего взаимодеймвтия с книгой*/
    private long DateOfLastStudy;

    public Book() { }

    public Book(String title, byte[] coverImage)
    {
        this.title = title;
        this.coverImage = coverImage;
        Words = new ArrayList<>(); /*!!!!!! ArrayList<Word>();*/
    }

    /* 1 - название, 2 - обложка, 3 - все слова с их частотами*/
    public Book(String title, byte[] coverImage, TreeMap<String, Integer> WordsI)
    {
        this.title = title;
        this.coverImage = coverImage;
        this.Words = new ArrayList<>();/*!!!!!! ArrayList<Word>();*/
        for (TreeMap.Entry<String, Integer> Word : WordsI.entrySet() )
        {
            Words.add(new Word(Word.getKey(), Word.getValue()));
        }
    }

    /*Здесь можно создать конструктор, в который будет передаваться путь к файлу*/

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

    public ArrayList<Word> getWords() {
        return Words;
    }

    public void setWords(ArrayList<Word> words) {
        Words = words;
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
