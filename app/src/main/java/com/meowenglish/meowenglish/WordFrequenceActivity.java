package com.meowenglish.meowenglish;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.meowenglish.meowenglish.data.Book;
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
        Book book = (Book) launchedIntent.getParcelableExtra("BOOK");

        getSupportActionBar().setTitle(bookTitle);

        TreeMap<String, Integer> wordFrequencies = book.getWordFrequencies();

        //Test:
//        wordFrequencies = new TreeMap<String, Integer>();
//        wordFrequencies.put("into", 304);
//        wordFrequencies.put("says", 235);
//        wordFrequencies.put("back", 221);
//        wordFrequencies.put("out", 207);
//        wordFrequencies.put("of", 199);
//        wordFrequencies.put("head", 178);
//        wordFrequencies.put("say", 165);
//        wordFrequencies.put("through", 162);
//        wordFrequencies.put("door", 152);

        WordsRecyclerAdapter wordsRecyclerAdapter = new WordsRecyclerAdapter(getApplicationContext(), book.getWordFrequencies());
        wordFrequenciesRecyclerView.setAdapter(wordsRecyclerAdapter);
        wordFrequenciesRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }
}