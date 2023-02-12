package com.mjc.school.repository.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class FileResourcesUtils {

    private FileResourcesUtils() {
    }

    public static List<String> getDataFromResourceFiles(String fileName) {
        ClassLoader classLoader = FileResourcesUtils.class.getClassLoader();
        List<String> fileContentByStrings = new ArrayList<>();

        try (InputStream resource = classLoader.getResourceAsStream(fileName);
             InputStreamReader streamReader = new InputStreamReader(resource, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(streamReader)) {
            String line;
            while ((line = reader.readLine()) != null) {
                fileContentByStrings.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("file not found " + fileName);

        }
        return fileContentByStrings;
    }

}
