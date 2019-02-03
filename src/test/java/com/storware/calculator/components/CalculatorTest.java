package com.storware.calculator.components;

import com.storware.calculator.dto.Screen;
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
import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CalculatorTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Autowired
    private Calculator calculator;

    @Test
    public void calculateCorrectInputParams() throws IOException {

        File correctFile = folder.newFile("myfile1.txt");
        Files.write(Paths.get(correctFile.getPath()), Collections.singleton("add 2\nmultiply 3\napply 10"));
        Screen correctFileScreen = new Screen(Arrays.asList("add 2","multiply 3", "apply 10"),36, "10 + 2 * 3");
        Screen result = calculator.calculate(correctFile.getPath());
        assertTrue(correctFileScreen.equals(result));

        correctFile = folder.newFile("myfile2.txt");
        Files.write(Paths.get(correctFile.getPath()), Collections.singleton("multiply 3\nadd 2\napply 10"));
        correctFileScreen = new Screen(Arrays.asList("multiply 3", "add 2", "apply 10"),32, "10 * 3 + 2");
        result = calculator.calculate(correctFile.getPath());
        assertTrue(correctFileScreen.equals(result));

        correctFile = folder.newFile("myfile3.txt");
        Files.write(Paths.get(correctFile.getPath()), Collections.singleton("apply 1"));
        correctFileScreen = new Screen(Arrays.asList("apply 1"),1, "1");
        result = calculator.calculate(correctFile.getPath());
        assertTrue(correctFileScreen.equals(result));
    }

    @Test
    public void calculateIncorrectInputParams() throws IOException {

        File correctFile = folder.newFile("myfile4.txt");
        Files.write(Paths.get(correctFile.getPath()), Collections.singleton("add 2\nmultiply 3\napplaaay 10"));
        Screen correctFileScreen = new Screen(Arrays.asList("add 2","multiply 3", "applaaay 10"),6, " + 2 * 3");
        Screen result = calculator.calculate(correctFile.getPath());
        assertTrue(correctFileScreen.equals(result));

        correctFile = folder.newFile("myfile5.txt");
        Files.write(Paths.get(correctFile.getPath()), Collections.singleton("add 2as\nmultiply 3\napply 10 zx"));
        correctFileScreen = new Screen(Arrays.asList("add 2as","multiply 3", "apply 10 zx"),3, " * 3");
        result = calculator.calculate(correctFile.getPath());
        assertTrue(correctFileScreen.equals(result));

        correctFile = folder.newFile("myfile6.txt");
        Files.write(Paths.get(correctFile.getPath()), Collections.singleton("apply 10 zx"));
        correctFileScreen = new Screen(Arrays.asList("apply 10 zx"),0, "");
        result = calculator.calculate(correctFile.getPath());
        assertTrue(correctFileScreen.equals(result));
    }

    @Test
    public void calculateExpressionResultCorrectInputParams(){

        int result = calculator.calculateExpressionResult("10 + 2 * 3");
        assertEquals(result, 36);

        result = calculator.calculateExpressionResult("10 * 3 + 2");
        assertEquals(result, 32);

        result = calculator.calculateExpressionResult("1");
        assertEquals(result, 1);

    }

    @Test
    public void calculateExpressionResultIncorrectInputParams(){

        int result = calculator.calculateExpressionResult(" + 2 * 3");
        assertEquals(result, 6);

        result = calculator.calculateExpressionResult("+ 2 * 3");
        assertEquals(result, 6);

        result = calculator.calculateExpressionResult(" * 3");
        assertEquals(result, 3);

        result = calculator.calculateExpressionResult("* 3");
        assertEquals(result, 3);
    }
}
