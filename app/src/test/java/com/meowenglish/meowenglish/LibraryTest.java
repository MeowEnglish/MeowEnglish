package com.meowenglish.meowenglish;

import com.meowenglish.meowenglish.data.Book;
import com.meowenglish.meowenglish.data.LibraryStorage;
import org.junit.Test;

import java.util.ArrayList;
import java.util.TreeMap;

import static org.junit.Assert.*;


public class LibraryTest {
    @Test
    public void getBook() {
        String title = "test";
        byte[] coverImage = {};
        TreeMap<String, Integer> wordFrequencies = new TreeMap<>();
        wordFrequencies.put("Dima", 4);

        Book firstBook = new Book(title, coverImage, wordFrequencies);
        LibraryStorage lib = new LibraryStorage();
        assertEquals(null, lib.getBook(firstBook));
    }

    @Test
    public void getBooks() {
        LibraryStorage lib = new LibraryStorage();
        assertEquals(new ArrayList<Book>(), lib.getBooks());
    }
}
