package com.meowenglish.meowenglish;

import org.junit.Test;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.Assert.*;
import com.meowenglish.meowenglish.Reader.logTableOfContents;
import nl.siegmann.epublib.domain.Book;


class ValueComparator implements Comparator<String>
{
    Map<String, Integer> base;

    public ValueComparator(Map<String, Integer> base) {
        this.base = base;
    }

    // Note: this comparator imposes orderings that are inconsistent with
    // equals.
    public int compare(String a, String b) {
        if (base.get(a) >= base.get(b)) {
            return -1;
        } else {
            return 1;
        } // returning 0 would merge keys
    }
}

public class WordFrequenceTest {

    @Test
    public void empty_book () {
        TreeMap<String, Integer> testBook = new TreeMap<String, Integer>();
        Book book = new Book();
        logTableOfContents log = new logTableOfContents();
        testBook = log.FlogTableOfContents(book);

        assertEquals(testBook, new TreeMap<String, Integer>());
    }

    @Test
    public void non_empty_book () {
        Map<String, Integer> words = new HashMap<String, Integer>();
        TreeMap<String, Integer> testbook = new TreeMap<String, Integer>();

        words.put("Igor", 0);  words.put("Dima", 1);
        testbook.put("Dima", 1);  testbook.put("Igor", 0);

        TreeMap<String, Integer> sorted_map = new TreeMap<String, Integer>(new ValueComparator(words));
        sorted_map.putAll(words);

        assertTrue(sorted_map.equals(testbook));
    }

    @Test
    public void non_empty_book_another () {
        Map<String, Integer> words = new HashMap<String, Integer>();
        TreeMap<String, Integer> testbook = new TreeMap<String, Integer>();

        words.put("Igor", 0);  words.put("Dima", 3); testbook.put("Daria", 4); testbook.put("Tatiana", 5); testbook.put("Daniil", 2);
        testbook.put("Tatiana", 5); testbook.put("Daria", 4); testbook.put("Dima", 3); testbook.put("Daniil", 2); testbook.put("Igor", 0);

        TreeMap<String, Integer> sorted_map = new TreeMap<String, Integer>(new ValueComparator(words));
        sorted_map.putAll(words);

        assertTrue(sorted_map.equals(testbook));
    }

}
