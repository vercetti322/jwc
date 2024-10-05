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
        StringBuilder temp = new StringBuilder("word freq:\n");
        for (int i = 0; i < Math.min(this.wordList.size(), 25); i++) {
            temp.append(this.wordList.get(i)).append("\n");
        }

        if (this.wordList.size() > 25) {
            temp.append("...").append("\n");
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
