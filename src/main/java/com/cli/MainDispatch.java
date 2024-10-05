package com.cli;

import com.cli.file.FileUtils;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.IOException;
import java.lang.reflect.Field;

@Command(name = "jwc",
        mixinStandardHelpOptions = true, version = "jwc 1.0.0",
        description = "mimics wc functionality of Unix on Windows"
)
public class MainDispatch implements Runnable {

    @Override
    public void run() {
        if (this.filepath == null || this.filepath.isEmpty()) {
            System.err.println("File path cannot be null or empty.");
            return;
        }

        FileUtils fileUtils;
        try {
            fileUtils = new FileUtils(this.filepath);
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
            return;
        }

        Field[] fields = this.getClass().getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);

            try {
                if (field.getType() == boolean.class && (boolean) field.get(this)) {
                    fileUtils.getVal(field.getName());
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Option(names = {"-b", "--bytes"}, description = "byte count in file")
    private boolean byteMode;

    @Option(names = {"-c", "--chars"}, description = "character count in file")
    private boolean charMode;

    @Option(names = {"-w", "--words"}, description = "word count in file")
    private boolean wordMode;

    @Option(names = {"-l", "--lines"}, description = "line count in file")
    private boolean lineMode;

    @Option(names = {"-L", "--max-line-length"}, description = "longest line in file")
    private boolean maxLineMode;

    @Option(names = {"-wf", "--word-frequency"}, description = "count per word in file")
    private boolean wordFreqMode;

    @Parameters(index = "0", paramLabel = "<filepath>", description = "path to the file")
    private String filepath;

    public static void main(String[] args) throws IOException {
        int exitCode = new CommandLine(new MainDispatch()).execute(args);
        System.exit(exitCode);
    }
}