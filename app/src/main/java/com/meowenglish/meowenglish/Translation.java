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
                    String ruWord = extractTranslation(line);

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

    private static String extractTranslation(String line) {
        int spaceIndex = line.indexOf(' ');
        String ruWord = line.substring(spaceIndex + 1);

        ruWord = removeBracketStatements(ruWord, '[', ']');

        ruWord = arrangeByNumbers(ruWord, new String[] {".", ">"});

        return ruWord;
    }

    private static String arrangeByNumbers(String text, String[] closeChars)
    {
        for (int i = 0; i < 10; i++)
        {
            for (int j = 0; j < closeChars.length; j++)
            {
                text = text.replace(" " + i + closeChars[j], "\n" + i + "");
            }
        }

        return text;
    }
    private static String removeBracketStatements(String text, char openBracket, char closeBracket)
    {
        int openBracketIndex = text.indexOf(openBracket);
        while (openBracketIndex >= 0)
        {
            openBracketIndex = text.indexOf(openBracket);
            if (openBracketIndex < 0)
            {
                return text;
            }
            Log.e("translation", "" + openBracketIndex);
            String startRuWord = text.substring(0, openBracketIndex);

            int closeBracketIndex = text.indexOf(closeBracket);
            if (closeBracketIndex < 0)
            {
                return text;
            }
            String finishRuWord = text.substring(closeBracketIndex + 1);
            text = startRuWord + finishRuWord;
        }

        return  text;
    }
}
