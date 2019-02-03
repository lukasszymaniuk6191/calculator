package com.storware.calculator.components;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FileReaderTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Autowired
    FileReader fileReader;

    @Test
    public void testCorrectFile() throws IOException {

        File correctFile = this.folder.newFile("myfile1.txt");
        Files.write(Paths.get(correctFile.getPath()), Collections.singleton("add 2\nmultiply 3\napplay 10"));

        List<String> fileLines = this.fileReader.readFile(correctFile.getAbsolutePath());

        assertEquals(fileLines.size(), 3);
        assertEquals(fileLines.get(0), "add 2");
        assertEquals(fileLines.get(1), "multiply 3");
        assertEquals(fileLines.get(2), "applay 10");
    }

    @Test
    public void testEmptyFile() throws IOException {

        File emptyFile = this.folder.newFile("myfile2.txt");
        List<String> fileLines = this.fileReader.readFile(emptyFile.getAbsolutePath());
        assertEquals(fileLines.size(), 0);
    }

    @Test
    public void testNotExisitingFile(){
        List<String> fileLines = this.fileReader.readFile("123/123/123");
        assertEquals(fileLines.size(), 0);
    }



}
