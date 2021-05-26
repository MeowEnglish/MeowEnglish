package com.meowenglish.meowenglish.ui.learnTab;

import android.content.Context;
import android.graphics.Movie;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.meowenglish.meowenglish.R;
import com.meowenglish.meowenglish.data.Word;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class WordCardAdapter extends ArrayAdapter<Word> {
    Context context;
    ArrayList<Word> words;

    public WordCardAdapter(@NonNull Context context, @NonNull ArrayList<Word> words) {
        super(context, 0, words);

        this.context = context;
        this.words = words;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(context).inflate(R.layout.word_card, parent, false);

        Word word = words.get(position);

        TextView text = (TextView) listItem.findViewById(R.id.word_text);
        text.setText(word.getText());

        TextView translationTextView = (TextView) listItem.findViewById(R.id.word_translate_text);
        String translationText = word.getTranslationText();
        translationTextView.setText(translationText);
        if (translationText == null || translationText.isEmpty())
        {
            listItem.findViewById(R.id.translate_loading).setVisibility(View.VISIBLE);
        }
        else
        {
            listItem.findViewById(R.id.translate_loading).setVisibility(View.GONE);
        }

        return listItem;
    }

//    @Override
//    public int getCount() {
//        return words.size();
//    }

//    @Override
//    public void notifyDataSetChanged() {
//        super.notifyDataSetChanged();
//    }
}
