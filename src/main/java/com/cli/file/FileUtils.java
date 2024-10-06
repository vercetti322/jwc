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
    private int sentences;
    private String avgWordLen;

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
            case "byteMode", "fullAnalysisMode":
                System.out.println("Bytes: " + this.bytes);
                if (!name.equals("fullAnalysisMode")) break;
            case "charMode":
                System.out.println("Chars: " + this.chars);
                if (!name.equals("fullAnalysisMode")) break;
            case "wordMode":
                System.out.println("Words: " + this.words);
                if (!name.equals("fullAnalysisMode")) break;
            case "lineMode":
                System.out.println("Lines: " + this.lines);
                if (!name.equals("fullAnalysisMode")) break;
            case "maxLineMode":
                System.out.println("Line (max): " + this.maxLineCount);
                if (!name.equals("fullAnalysisMode")) break;
            case "wordFreqMode":
                System.out.println(this.wordHash);
                if (!name.equals("fullAnalysisMode")) break;
            case "avgWordLenMode":
                System.out.println("Avg word length: " + this.avgWordLen);
                break;
        }

    }

    private void getWords() {
        int count = 0; long sum = 0L;
        String[] words = this.strFile.split("\\s+");

        for (String word : words) {
            if (!word.isEmpty()) {
                sum += word.length();
                this.wordHash.addWord(new Word(word, 1));
                count++;
            }
        }

        this.words = count;
        if (this.words > 0 ) {
            this.avgWordLen = String.format("%.2f", (double) sum / this.words);
        } else {
            this.avgWordLen = "0.0";
        }
    }

    private void getLines() {
        int idx = 0, count = 0;
        while (idx < this.strFile.length()) {
            int prev = idx;
            while (idx < this.strFile.length() && this.strFile.charAt(idx) != '\n') {
                idx++;
            }

            this.maxLineCount = Math.max(this.maxLineCount, idx - prev);
            count++;

            if (idx < this.strFile.length())
                idx++;
        }

        this.lines = count;
    }
}
