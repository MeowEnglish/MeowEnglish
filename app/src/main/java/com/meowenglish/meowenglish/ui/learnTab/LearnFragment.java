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
import com.meowenglish.meowenglish.data.Word;

import java.util.ArrayList;

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

        InitWords();

        wordsFrame = root.findViewById(R.id.learn_word_frame);
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

                view.findViewById(R.id.left_indicator).setAlpha(scrollProgress > 0 ? scrollProgress : 0);
                view.findViewById(R.id.right_indicator).setAlpha(scrollProgress < 0 ? -scrollProgress : 0);
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
        words.add(new Word("test0", 0));
        words.add(new Word("test1", 0));
        words.add(new Word("test2", 0));
        words.add(new Word("test3", 0));
    }
}