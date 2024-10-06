package com.cli.hashset;

import java.util.ArrayList;

public class WordSet {

    private final ArrayList<Word> wordList;

    public WordSet() {
        this.wordList = new ArrayList<>();
    }

    public Word getWord(int idx) {
        return this.wordList.get(idx);
    }

    public ArrayList<Word> getWordList() {
        return this.wordList;
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

    @Override
    public String toString() {
        this.mergeSort(0, this.wordList.size() - 1);
        StringBuilder temp = new StringBuilder("Top 10 words : [");
        for (int i = 0; i < Math.min(this.wordList.size(), 10); i++) {
            if (i < Math.min(this.wordList.size(), 10) - 1) {
                temp.append(this.wordList.get(i)).append(", ");
            } else {
                temp.append(this.wordList.get(i));
            }
        }

        if (this.wordList.size() > 10) {
            temp.append("]");
        }

        return temp.toString();
    }

    public void mergeSort(int start, int end) {
        if (end > start) {
            int mid = (start + end) / 2;
            this.mergeSort(start, mid);
            this.mergeSort(mid + 1, end);
            this.merge(start, mid, end);
        }
    }

    public void merge(int start, int mid, int end) {
        WordSet temp = new WordSet();
        int i = start, j = mid + 1;
        while (i <= mid && j <= end) {
            if (this.getWord(i).getCount() >= this.getWord(j).getCount()) {
                temp.addWord(this.getWord(i));
                i++;
            } else {
                temp.addWord(this.getWord(j));
                j++;
            }
        }

        while (i <= mid) {
            temp.addWord(this.getWord(i));
            i++;
        }

        while (j <= end) {
            temp.addWord(this.getWord(j));
            j++;
        }

        for (int k = 0; k < temp.wordList.size(); k++) {
            this.wordList.set(k + start, temp.getWord(k));
        }
    }
}
