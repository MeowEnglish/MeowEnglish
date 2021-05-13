package com.meowenglish.meowenglish.data;

public class Word
{
    /*Слово*/
    private String text;

    /*Число повторений*/
    private int repeatCount;

    /*Число повторений слова в книге*/
    private int frequency;

    /*Последняя активность со словом,
        *после изучения - время окончания обучения*/
    private long lastActivityTime;

    /*Время начала обучения слова*/
    private long studyStartTime;

    public Word() {}
    public Word(String text)
    {
        this.text = text;
        repeatCount = 0;
    }
    public Word(String text, int frequency)
    {
        this.text = text;
        this.frequency = frequency;
        repeatCount = 0;
    }


    @Override
    public boolean equals(Object otherWord) {
        // If the object is compared with itself then return true
        if (otherWord == this) {
            return true;
        }

        /* Check if o is an instance of Complex or not
          "null instanceof [type]" also returns false */
        if (!(otherWord instanceof Word)) {
            return false;
        }

        // Compare the data members and return accordingly
        return this.text.equals(((Word) otherWord).text);
    }
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + (this.text != null ? this.text.hashCode() : 0);
        return hash;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getRepeatCount() {
        return repeatCount;
    }

    public void setRepeatCount(int repeatCount) {
        this.repeatCount = repeatCount;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public long getLastActivityTime() {
        return lastActivityTime;
    }

    public void setLastActivityTime(long lastActivityTime) {
        this.lastActivityTime = lastActivityTime;
    }

    public long getStudyStartTime() {
        return studyStartTime;
    }

    public void setStudyStartTime(long studyStartTime) {
        this.studyStartTime = studyStartTime;
    }
}
