package com.cli.hashset;

import java.util.ArrayList;

public class WordSet {

    private final ArrayList<Word> wordList;

    public WordSet() {
        this.wordList = new ArrayList<>();
    }

    public void addWord(Word word) {
        for (Word value : this.wordList) {
            if (word.getName().equals(value.getName())) {
                value.increment();
                return;
            }
        }

        this.wordList.add(word);
    }

    public boolean containsByName(String wordName) {
        for (Word word: this.wordList) {
            if (word.getName().equals(wordName)) {
                return true;
            }
        }

        return false;
    }

    public void increment(String wordName) {
        for (Word word: this.wordList) {
            if (word.getName().equals(wordName)) {
                word.increment();
                return;
            }
        }
    }

    @Override
    public String toString() {
        this.sort();
        StringBuilder temp = new StringBuilder("word freq:\n");
        for (Word word: this.wordList) {
            temp.append(word).append("\n");
        }

        return temp.toString();
    }

    public void sort() {
        for (int i = 0; i < this.wordList.size() - 1; i++) {
            int maxIdx = i;
            for (int j = i + 1; j < this.wordList.size(); j++) {
                if (this.wordList.get(j).getCount()
                        > this.wordList.get(maxIdx).getCount()) {
                    maxIdx = j;
                }
            }

            if (maxIdx != i) {
                this.swap(i, maxIdx);
            }
        }
    }

    private void swap(int i, int j) {
        if (i < 0 || i >= this.wordList.size() || j < 0 || j >= this.wordList.size()) {
            throw new IndexOutOfBoundsException("Index out of bounds for swap operation");
        }

        Word temp = this.wordList.get(i);
        this.wordList.set(i, this.wordList.get(j));
        this.wordList.set(j, temp);
    }
}
