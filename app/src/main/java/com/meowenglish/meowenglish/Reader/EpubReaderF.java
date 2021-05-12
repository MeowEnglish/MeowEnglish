package com.meowenglish.meowenglish.Reader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import nl.siegmann.epublib.epub.EpubReader;
import nl.siegmann.epublib.domain.Book;

public class EpubReaderF
{
    private EpubReader epubReader;

    public EpubReaderF()
    {
        epubReader = new EpubReader();
    }

    public Book ReadFile(String fileName)
    {
        FileInputStream epubInputStream = null;
        Book book = null;
        try
        {
            File file = new File(fileName);

            if (file.exists())
            {
                epubInputStream = new FileInputStream(file);

                book = epubReader.readEpub(epubInputStream);
            }
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        return book;
    }
}