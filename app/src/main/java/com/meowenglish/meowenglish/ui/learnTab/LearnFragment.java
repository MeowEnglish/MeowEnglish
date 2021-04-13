package com.meowenglish.meowenglish.ui.learnTab;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.meowenglish.meowenglish.R;

public class LearnFragment extends Fragment {

    private LearnViewModel learnViewModel;

    @SuppressLint("RestrictedApi")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        learnViewModel = new ViewModelProvider(this).get(LearnViewModel.class);
        View root = inflater.inflate(R.layout.fragment_learn, container, false);

        return root;
    }
}