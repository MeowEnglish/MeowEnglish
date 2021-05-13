package com.meowenglish.meowenglish.ui.libraryTab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.meowenglish.meowenglish.R;
import com.meowenglish.meowenglish.data.Book;
import com.meowenglish.meowenglish.data.LibraryStorage;
import com.meowenglish.meowenglish.data.Word;
import com.meowenglish.meowenglish.ui.libraryTab.WordsRecyclerAdapter;

import java.util.TreeMap;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class WordFrequenceActivity extends AppCompatActivity {
    private RecyclerView wordFrequenciesRecyclerView;
    private Book book;
    private TreeMap<String, Integer> wordFrequencies;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_frequence);

        wordFrequenciesRecyclerView = findViewById(R.id.wordFrequencies);

        Intent launchedIntent = getIntent();
        String bookTitle = launchedIntent.getStringExtra(Intent.EXTRA_TEXT);
        book = (Book) launchedIntent.getParcelableExtra(Book.PARCELABLE_EXTRA_NAME);
        book = ((LibraryStorage) getApplication()).getData().getBook(book);

        if (book == null)
        {
            return;
        }

        wordFrequencies = book.getWordFrequencies();

        getSupportActionBar().setTitle(bookTitle);

        WordsRecyclerAdapter wordsRecyclerAdapter = new WordsRecyclerAdapter(getApplicationContext(), wordFrequencies);
        wordFrequenciesRecyclerView.setAdapter(wordsRecyclerAdapter);
        wordFrequenciesRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                String swipedWord = (String) ((WordsRecyclerAdapter.ViewHolder)viewHolder).getWordTextView().getText();
                Integer swipedFrequency = Integer.parseInt((String) ((WordsRecyclerAdapter.ViewHolder)viewHolder).getFrequencyTextView().getText());

                switch (direction)
                {
                    //make a word known
                    case ItemTouchHelper.LEFT:
                    {
                        ((LibraryStorage) getApplication()).addWordToKnown(new Word(swipedWord, swipedFrequency));
                        ((LibraryStorage) getApplication()).removeWordFromBooks(swipedWord);
                        wordsRecyclerAdapter.notifyItemRemoved(position);

                        Snackbar.make(wordFrequenciesRecyclerView, "Слово \"" + swipedWord + "\" добавлено в словарь", BaseTransientBottomBar.LENGTH_LONG)
                                .setAction("Вернуть", v -> {
                                    wordFrequencies.put(swipedWord, swipedFrequency);

                                    ((LibraryStorage) getApplication()).addWordToBook(book, new Word(swipedWord, swipedFrequency));
                                    ((LibraryStorage) getApplication()).removeWordFromKnown(new Word(swipedWord, swipedFrequency));

                                    wordsRecyclerAdapter.notifyItemInserted(position);
                                }).show();

                        break;
                    }
                }
            }

            @Override
            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                        .addSwipeLeftBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.known_words_color))
                        .addSwipeLeftActionIcon(R.drawable.ic_baseline_book_24)
                        .setSwipeLeftLabelTextSize(TypedValue.COMPLEX_UNIT_SP,20)
                        .setSwipeLeftActionIconTint(ContextCompat.getColor(getApplicationContext(), R.color.white))
                        .addSwipeLeftLabel("Уже знаю")
                        .setSwipeLeftLabelColor(ContextCompat.getColor(getApplicationContext(), R.color.white))
                        .create()
                        .decorate();

                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        }).attachToRecyclerView(wordFrequenciesRecyclerView);
    }
}