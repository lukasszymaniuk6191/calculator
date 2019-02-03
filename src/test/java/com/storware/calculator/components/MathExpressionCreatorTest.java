package com.storware.calculator.components;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MathExpressionCreatorTest {

    @Autowired
    private MathExpressionCreator mathExpressionCreator;

    @Test
    public void convertSingleLineToMathExpressionCorrectInputParams(){

        String result = mathExpressionCreator.convertSingleLineToMathExpression("add 325");
        assertTrue(Pattern.compile( "[0-9]" ).matcher( result ).find());
        assertTrue(Pattern.compile( "[+]" ).matcher( result ).find());
        assertEquals(result.length(), 6);
        assertEquals(result, " + 325");

        result = mathExpressionCreator.convertSingleLineToMathExpression("subtract 325");
        assertTrue(Pattern.compile( "[0-9]" ).matcher( result ).find());
        assertTrue(Pattern.compile( "[-]" ).matcher( result ).find());
        assertEquals(result.length(), 6);
        assertEquals(result, " - 325");

        result = mathExpressionCreator.convertSingleLineToMathExpression("multiply 325");
        assertTrue(Pattern.compile( "[0-9]" ).matcher( result ).find());
        assertTrue(Pattern.compile( "[*]" ).matcher( result ).find());
        assertEquals(result.length(), 6);
        assertEquals(result, " * 325");

        result = mathExpressionCreator.convertSingleLineToMathExpression("divide 325");
        assertTrue(Pattern.compile( "[0-9]" ).matcher( result ).find());
        assertTrue(Pattern.compile( "[/]" ).matcher( result ).find());
        assertEquals(result.length(), 6);
        assertEquals(result, " / 325");

        result = mathExpressionCreator.convertSingleLineToMathExpression("apply 325");
        assertTrue(Pattern.compile( "[0-9]" ).matcher( result ).find());
        assertEquals(result.length(), 6);
        assertEquals(result, "app325");

    }

    @Test
    public void convertSingleLineToMathExpressionIncorrectInputParams(){

        String result = mathExpressionCreator.convertSingleLineToMathExpression("add325");
        assertNull(result);

        result = mathExpressionCreator.convertSingleLineToMathExpression("add 325 asd");
        assertNull(result);

        result = mathExpressionCreator.convertSingleLineToMathExpression("add 325asd");
        assertNull(result);

        result = mathExpressionCreator.convertSingleLineToMathExpression("add123 325");
        assertNull(result);

        result = mathExpressionCreator.convertSingleLineToMathExpression("a 1 b 2 c 3");
        assertNull(result);

        result = mathExpressionCreator.convertSingleLineToMathExpression("111 222");
        assertNull(result);

        result = mathExpressionCreator.convertSingleLineToMathExpression("aaa bbb");
        assertNull(result);

        result = mathExpressionCreator.convertSingleLineToMathExpression(" ");
        assertNull(result);

        result = mathExpressionCreator.convertSingleLineToMathExpression("");
        assertNull(result);

    }


    @Test
    public void mathExpressionCreatorCorrectInputParams(){

        List<String> params = new ArrayList<>();
        params.add("add 2");
        params.add("multiply 3");
        params.add("apply 10");

        String result = mathExpressionCreator.createMathExpression(params);
        assertEquals("10 + 2 * 3", result);


        params = new ArrayList<>();
        params.add("multiply 3");
        params.add("add 2");
        params.add("apply 10");

        result = mathExpressionCreator.createMathExpression(params);
        assertEquals("10 * 3 + 2", result);


        params = new ArrayList<>();
        params.add("apply 1");

        result = mathExpressionCreator.createMathExpression(params);
        assertEquals("1", result);


        params = new ArrayList<>();
        params.add("multiply 3");
        params.add("apply 10");
        params.add("add 2");
        params.add("apply 10");

        result = mathExpressionCreator.createMathExpression(params);
        assertEquals("1010 * 3 + 2", result);

    }


    @Test
    public void mathExpressionCreatorIncorrectInputParams(){

        List<String> params = new ArrayList<>();
        params.add("addq 2");
        params.add("multiply 3");
        params.add("apply 10");

        String result = mathExpressionCreator.createMathExpression(params);
        assertEquals("10 * 3", result);


        params = new ArrayList<>();
        params.add("addq2");
        params.add("multiply 3");
        params.add("applyghj 10");

        result = mathExpressionCreator.createMathExpression(params);
        assertEquals(" * 3", result);


        params = new ArrayList<>();
        params.add("addq2");
        params.add("3");
        params.add("applyghj 10");

        result = mathExpressionCreator.createMathExpression(params);
        assertEquals("", result);


        params = new ArrayList<>();

        result = mathExpressionCreator.createMathExpression(params);
        assertEquals("", result);

    }


}
