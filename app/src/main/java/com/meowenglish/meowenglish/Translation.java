package com.meowenglish.meowenglish;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public final class Translation {
    public static String Translate(Context context, String text)
    {
        InputStream dictFile;
        try
        {
            dictFile = context.getResources().openRawResource(R.raw.en_ru_dictionary);
        }
        catch (Exception e)
        {
            return e.getMessage();
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(dictFile, "UTF-8"))) {
            String line;
            while ((line = br.readLine()) != null) {
                int spaceIndex = line.indexOf(' ');
                String enWord = line.substring(0, spaceIndex);
                if (enWord.equals(text))
                {
                    String ruWord = line.substring(spaceIndex + 1);
                    return ruWord;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }
}
