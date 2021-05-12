package com.meowenglish.meowenglish;

import org.junit.Test;
import com.meowenglish.meowenglish.data.Book;

import java.util.TreeMap;
import static org.junit.Assert.*;

public class BookTest {
    @Test
    public void equals_book() {
        String title = "test";
        byte[] coverImage = {};
        TreeMap<String, Integer> wordFrequencies = new TreeMap<>();
        wordFrequencies.put("Dima", 4);

        Book firstBook = new Book(title, coverImage, wordFrequencies);
        Book secondBook = new Book(title, coverImage, wordFrequencies);

        assertTrue(firstBook.equals(secondBook));
    }

    @Test
    public void get_title_book() {
        String title = "test";
        byte[] coverImage = {};
        TreeMap<String, Integer> wordFrequencies = new TreeMap<>();
        wordFrequencies.put("Dima", 4);

        Book firstBook = new Book(title, coverImage, wordFrequencies);

        assertEquals(firstBook.getTitle(), title);
    }

    @Test
    public void get_coverImage_book() {
        String title = "test";
        byte[] coverImage = {};
        TreeMap<String, Integer> wordFrequencies = new TreeMap<>();
        wordFrequencies.put("Dima", 4);

        Book firstBook = new Book(title, coverImage, wordFrequencies);

        assertEquals(firstBook.getCoverImage(), coverImage);
    }
}
