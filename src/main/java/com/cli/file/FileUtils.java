package com.cli.file;

import com.cli.hashset.Word;
import com.cli.hashset.WordSet;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

public class FileUtils {

    private final WordSet wordHash;
    private final String strFile;
    private final int chars;
    private final int bytes;
    private int maxLineCount;
    private int lines;
    private int words;

    public FileUtils(String filepath) throws IOException {
        Path path = Paths.get(filepath);
        this.bytes = Files.readAllBytes(path).length;
        this.strFile = Files.readString(path);
        this.chars = this.strFile.length();
        this.wordHash = new WordSet();
        this.getWords(); this.getLines();
    }

    public void getVal(String name) {
        switch (name) {
            case "byteMode":
                System.out.println("bytes: " + this.bytes);
                break;
            case "charMode":
                System.out.println("chars: " + this.chars);
                break;
            case "wordMode":
                System.out.println("words: " + this.words);
                break;
            case "lineMode":
                System.out.println("lines: " + this.lines);
                break;
            case "maxLineMode":
                System.out.println("line (max): " + this.maxLineCount);
                break;
            case "wordFreqMode":
                System.out.println(this.wordHash);
        }
    }

    private void getWords() {
        int count = 0;
        String[] words = this.strFile.split("\\W+");

        for (String word : words) {
            if (!word.isEmpty()) {
                this.wordHash.addWord(new Word(word, 1));
                count++;
            }
        }

        this.words = count;
    }

    private void getLines() {
        int idx = 0, count = 0;
        while (idx < this.strFile.length()) {
            int prev = idx;
            while (idx < this.strFile.length() && this.strFile.charAt(idx) != '\n')
                idx++;

            this.maxLineCount = Math.max(this.maxLineCount, idx - prev);
            count++;

            if (idx < this.strFile.length())
                idx++;
        }

        this.lines = count;
    }
}
