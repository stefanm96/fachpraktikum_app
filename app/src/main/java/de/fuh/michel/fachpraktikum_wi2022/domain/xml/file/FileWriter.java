package de.fuh.michel.fachpraktikum_wi2022.domain.xml.file;

import java.io.File;
import java.io.IOException;

public class FileWriter {

    private final File filesDir;

    public FileWriter(File filesDir) {
        this.filesDir = filesDir;
    }

    public void writeToFile(String filename, String content) throws IOException {
        File outputFile = new File(filesDir, filename);
        java.io.FileWriter fileWriter = new java.io.FileWriter(outputFile);
        fileWriter.write(content);
        fileWriter.flush();
        fileWriter.close();
    }
}
