package com.meowenglish.meowenglish.data;

import java.util.ArrayList;

public class User
{
    /*Имя пользователя*/
    private String Name;

    /*Почта пользователя*/
    private String Email;

    /*Список книг пользователя*/
    private ArrayList<Book> Books;

    /*Список друзей*/
    private ArrayList<String> Friends;

    /*Список котиков*/
    private ArrayList<String> Cats;

    /*Последние действия пользователя*/
    private long LastActivity;

    /*Пол пользователя*/
    private String Gender;

    /*Общее число выученных слов*/
    private int NumOfWdLearned;

    /*Род деятельности*/
    private String Occupation;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public ArrayList<Book> getBooks() {
        return Books;
    }

    public void setBooks(ArrayList<Book> books) {
        Books = books;
    }

    public ArrayList<String> getFriends() {
        return Friends;
    }

    public void setFriends(ArrayList<String> friends) {
        Friends = friends;
    }

    public ArrayList<String> getCats() {
        return Cats;
    }

    public void setCats(ArrayList<String> cats) {
        Cats = cats;
    }

    public long getLastActivity() {
        return LastActivity;
    }

    public void setLastActivity(long lastActivity) {
        LastActivity = lastActivity;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public int getNumOfWdLearned() {
        return NumOfWdLearned;
    }

    public void setNumOfWdLearned(int numOfWdLearned) {
        NumOfWdLearned = numOfWdLearned;
    }

    public String getOccupation() {
        return Occupation;
    }

    public void setOccupation(String occupation) {
        Occupation = occupation;
    }

    public User() {}

    public User(String name, String email, String gender, String occupation ) {
        Name = name;
        Email = email;
        Gender = gender;
        Occupation = occupation;
    }

    public User(String name, String email, String gender) {
        Name = name;
        Email = email;
        Gender = gender;
        Occupation = "undefined";
    }

    public User(String name, String email) {
        Name = name;
        Email = email;
        Gender = "undefined";
        Occupation = "undefined";
    }
}
