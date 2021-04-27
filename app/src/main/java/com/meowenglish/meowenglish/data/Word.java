package com.meowenglish.meowenglish.data;

public class Word
{
    /*Слово*/
    private String Wd;

    /*Состояние слова*/
    private byte State;

    /*Число повторений*/
    private byte NumOfRepet;

    /*Число повторений слова в книге*/
    private int Weight;

    /*Последняя активность со словом,
        *после изучения - время окончания обучения*/
    private long LastActivity;

    /*Время начала обучения слова*/
    private long StudyStartTime;

    public Word() {}

    public Word(String Wd)
    {
        this.Wd = Wd;
        NumOfRepet = 0;
    }

    public Word(String Wd, int Weight)
    {
        this.Wd = Wd;
        this.Weight = Weight;
        NumOfRepet = 0;
    }

    public String getWd() {
        return Wd;
    }

    public void setWd(String wd) {
        Wd = wd;
    }

    public byte getState() {
        return State;
    }

    public void setState(byte state) {
        State = state;
    }

    public byte getNumOfRepet() {
        return NumOfRepet;
    }

    public void setNumOfRepet(byte numOfRepet) {
        NumOfRepet = numOfRepet;
    }

    public int getWeight() {
        return Weight;
    }

    public void setWeight(int weight) {
        Weight = weight;
    }

    public long getLastActivity() {
        return LastActivity;
    }

    public void setLastActivity(long lastActivity) {
        LastActivity = lastActivity;
    }

    public long getStudyStartTime() {
        return StudyStartTime;
    }

    public void setStudyStartTime(long studyStartTime) {
        StudyStartTime = studyStartTime;
    }
}
