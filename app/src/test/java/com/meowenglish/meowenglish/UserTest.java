package com.meowenglish.meowenglish;

import org.junit.Test;
import static org.junit.Assert.*;
import com.meowenglish.meowenglish.data.User;
import com.meowenglish.meowenglish.data.Book;

import java.util.ArrayList;
import java.util.TreeMap;

public class UserTest {

    @Test
    public void getName() {
        User user = new User("Igor", "IgorTOPpacan@gmail.com");
        assertEquals("Igor", user.getName());
    }

    @Test
    public void getBooks() {
        User user = new User("Igor", "IgorTOPpacan@gmail.com");
        ArrayList<Book> books = new ArrayList<>();

        String title = "test";
        byte[] coverImage = {};
        TreeMap<String, Integer> wordFrequencies = new TreeMap<>();
        wordFrequencies.put("Dima", 4);

        Book firstBook = new Book(title, coverImage, wordFrequencies);
        Book secondBook = new Book(title, coverImage, wordFrequencies);
        books.add(firstBook); books.add(secondBook);
        user.setBooks(books);

        assertEquals(books, user.getBooks());
    }

    @Test
    public void getGender() {
        User user = new User("Igor", "IgorTOPpacan@gmail.com");
        user.setGender("straight");
        assertEquals("straight", user.getGender());
    }
}
