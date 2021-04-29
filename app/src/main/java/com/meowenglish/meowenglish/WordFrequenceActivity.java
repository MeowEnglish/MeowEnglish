package com.meowenglish.meowenglish;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.meowenglish.meowenglish.data.Book;
import com.meowenglish.meowenglish.data.LibraryStorage;
import com.meowenglish.meowenglish.ui.libraryTab.WordsRecyclerAdapter;

import java.util.TreeMap;

public class WordFrequenceActivity extends AppCompatActivity {
    private RecyclerView wordFrequenciesRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_frequence);

        wordFrequenciesRecyclerView = findViewById(R.id.wordFrequencies);

        Intent launchedIntent = getIntent();
        String bookTitle = launchedIntent.getStringExtra(Intent.EXTRA_TEXT);
        Book book = (Book) launchedIntent.getParcelableExtra(Book.PARCELABLE_EXTRA_NAME);
        book = ((LibraryStorage) getApplication()).getBook(book);

//        if (book == null)
//        {
//            return;
//        }

        getSupportActionBar().setTitle(bookTitle);

        WordsRecyclerAdapter wordsRecyclerAdapter = new WordsRecyclerAdapter(getApplicationContext(), book.getWordFrequencies());
        wordFrequenciesRecyclerView.setAdapter(wordsRecyclerAdapter);
        wordFrequenciesRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }
}