package com.cli.hashset;

public class Word {

    private final String word;
    private int count;

    public Word(String word, int count) {
        this.word = word;
        this.count = count;
    }

    public int getCount() {
        return this.count;
    }

    @Override
    public String toString() {
        return this.word + ": " + this.count;
    }

    public String getName() {
        return this.word;
    }

    public void increment() {
        this.count++;
    }
}
