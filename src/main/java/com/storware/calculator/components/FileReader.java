package com.storware.calculator.components;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Component
public class FileReader {

    public List<String> readFile(String filePath){


        List<String> allLines = new ArrayList<>();

        try {
            allLines = Files.readAllLines(Paths.get(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return allLines;
    }

}
