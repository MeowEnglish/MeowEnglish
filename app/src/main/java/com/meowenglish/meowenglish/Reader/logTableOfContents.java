package com.meowenglish.meowenglish.Reader;

import android.text.Html;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.*;

import nl.siegmann.epublib.domain.TOCReference;
import nl.siegmann.epublib.domain.Book;

public class logTableOfContents
{
    public TreeMap<String, Integer> FlogTableOfContents(Book book)
    {
        List<TOCReference> tocReferences = book.getTableOfContents().getTocReferences();

        Map<String, Integer> words = new HashMap<String, Integer>();

        for (TOCReference tocReference : tocReferences)
        {
            Log.i("TOC", tocReference.getTitle());

            try
            {
                InputStream inputStream = tocReference.getResource().getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                int countRepWords;
                String line = "";
                while ((line = bufferedReader.readLine()) != null)
                {
                    String line1 = Html.fromHtml(line).toString();

                    String[] formatedWords = line1.replaceAll("[^A-za-z ]", "").split("\\s+");

                    for (String formatedWord : formatedWords)
                    {
                        if (!formatedWord.isEmpty())
                        {
                            formatedWord = formatedWord.toLowerCase();

                            countRepWords = words.containsKey(formatedWord) ? words.get(formatedWord) + 1 : 1;
                            words.put(formatedWord, countRepWords);
                        }
                    }
                }
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        TreeMap<String, Integer> sorted_map = new TreeMap<String, Integer>(new ValueComparator(words));
        sorted_map.putAll(words);

        return sorted_map;
    }

    @SuppressWarnings("unused")
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
}
