package com.meowenglish.meowenglish.ui.libraryTab;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.meowenglish.meowenglish.R;

import java.util.TreeMap;

public class WordsRecyclerAdapter extends RecyclerView.Adapter<WordsRecyclerAdapter.ViewHolder>
{
    private Context context;

    private int length;
    private String[] wordsArray;
    private Integer[] frequenciesArray;


    public WordsRecyclerAdapter(Context context, TreeMap<String, Integer> wordFrequencies)
    {
        this.context = context;

        length = wordFrequencies.size();
        wordsArray = wordFrequencies.keySet().toArray(new String[length]);
        frequenciesArray = wordFrequencies.values().toArray(new Integer[length]);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.word_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.word.setText(wordsArray[position]);
        holder.frequency.setText(frequenciesArray[position].toString());
    }

    @Override
    public int getItemCount() {
        return length;
    }


    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView word;
        TextView frequency;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            word = itemView.findViewById(R.id.word);
            frequency = itemView.findViewById(R.id.frequency);
        }


        public TextView getWordTextView() { return word; }
        public TextView getFrequencyTextView() { return frequency; }
    }
}
