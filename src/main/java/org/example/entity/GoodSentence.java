package org.example.entity;

public class GoodSentence {
    private String sentence;
    private String writer;
    private int num;

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public GoodSentence(int num, String sentence, String writer) {
        this.sentence = sentence;
        this.writer = writer;
        this.num = num;
    }

    public String toString() {
        return num + " / " + writer + " / " + sentence;
    }
}