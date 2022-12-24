package de.fuh.michel.fachpraktikum_wi2022.domain.xml;

import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

public class FileProvider {

    private static final String TAG = "FileProvider";

    private final File filesDir;

    public FileProvider(File filesDir) {
        this.filesDir = filesDir;
    }

    public List<String> getAllFiles() {
        return Arrays.asList(filesDir.list());
    }

    public String getFileContent(String fileName) throws IOException {
        Log.i(TAG, "Getting xml resource by filename: " + fileName);

        String[] list = filesDir.list();

        for (String s : list) {
            Log.i(TAG, "File: " + s);
        }

        File file = new File(filesDir, fileName);

        String content = getContent(file);

        Log.i(TAG, "getXmlContent: " + content);

        return content;
    }

    private String getContent(File file) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        InputStreamReader inputStreamReader =
                new InputStreamReader(fis, StandardCharsets.UTF_8);
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(inputStreamReader)) {
            String line = reader.readLine();
            while (line != null) {
                stringBuilder.append(line).append('\n');
                line = reader.readLine();
            }
            return stringBuilder.toString();
        }
    }
}
