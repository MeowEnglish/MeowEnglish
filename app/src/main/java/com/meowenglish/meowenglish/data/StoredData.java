package com.meowenglish.meowenglish.data;

import java.util.ArrayList;

public class StoredData {
    public ArrayList<Book> books = new ArrayList<>();
    public ArrayList<Word> words = new ArrayList<>();


    public Book getBook(Book book) {
        for (Book curBook : books)
        {
            if (curBook.getTitle().equals(book.getTitle()))
            {
                return curBook;
            }
        }

        return null;
    }
}
