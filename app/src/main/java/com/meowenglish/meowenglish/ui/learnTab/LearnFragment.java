package com.meowenglish.meowenglish.ui.learnTab;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.lorentzos.flingswipe.SwipeFlingAdapterView;
import com.meowenglish.meowenglish.R;
import com.meowenglish.meowenglish.data.Book;
import com.meowenglish.meowenglish.data.LibraryStorage;
import com.meowenglish.meowenglish.data.Word;

import java.util.ArrayList;
import java.util.TreeMap;

public class LearnFragment extends Fragment {

    private LearnViewModel learnViewModel;
    private ArrayList<Word> words;
    private ArrayAdapter<Word> wordArrayAdapter;
    private SwipeFlingAdapterView wordsFrame;

    @SuppressLint("RestrictedApi")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        learnViewModel = new ViewModelProvider(this).get(LearnViewModel.class);
        View root = inflater.inflate(R.layout.fragment_learn, container, false);

        wordsFrame = root.findViewById(R.id.learn_word_frame);

        InitWords();

        wordArrayAdapter = new ArrayAdapter<Word>(getContext(), R.layout.word_card, R.id.word_text, words);

        wordsFrame.setAdapter(wordArrayAdapter);
        wordsFrame.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                words.remove(0);
                wordArrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object o) {
                Toast.makeText(getContext(), "Left", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRightCardExit(Object o) {
                Toast.makeText(getContext(), "Right", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdapterAboutToEmpty(int i) {
                words.add(new Word("laaaast word"));
                wordArrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onScroll(float scrollProgress) {
                View view = wordsFrame.getSelectedView();

                view.findViewById(R.id.know_button).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        moveLeft();
                    }
                });
                view.findViewById(R.id.dont_know_button).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        moveRight();
                    }
                });

                view.findViewById(R.id.right_indicator).setAlpha(scrollProgress > 0 ? scrollProgress : 0);
                view.findViewById(R.id.left_indicator).setAlpha(scrollProgress < 0 ? -scrollProgress : 0);
            }
        });

        return root;
    }


    private void moveLeft()
    {
        wordsFrame.getTopCardListener().selectLeft();
    }
    private void moveRight()
    {
        wordsFrame.getTopCardListener().selectRight();
    }
    private void InitWords()
    {
        words = new ArrayList<>();

        Book book = ((LibraryStorage)getActivity().getApplication()).getData().getLastBook();

        TreeMap<String, Integer> wordFrequencies = (TreeMap<String, Integer>) book.getWordFrequencies().clone();
        int length = 20;
        for (int i = 0; i < length; i++)
        {
            words.add(new Word(wordFrequencies.pollFirstEntry().getKey(), 0));
        }
    }
}