package com.storware.calculator.components;

import com.storware.calculator.dto.Screen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Calculator {

    private FileReader fileReader;
    private MathExpressionCreator mathExpressionCreator;

    @Autowired
    public Calculator(FileReader fileReader, MathExpressionCreator mathExpressionCreator){
        this.fileReader = fileReader;
        this.mathExpressionCreator = mathExpressionCreator;
    }

    public Screen calculate(String filePath){

        List<String> allFileLines = this.fileReader.readFile(filePath);
        String mathExpression = null;
        int matExpressionResult = 0;

        if(allFileLines.size()>0){
            mathExpression = mathExpressionCreator.createMathExpression(allFileLines);
        }else {
            System.out.println("The specified file does not exist or is empty");
        }

        if(mathExpression.length()>0){
            matExpressionResult = this.calculateExpressionResult(mathExpression);
        }else {
            System.out.println("The data read from the file is incorrect");
        }

        return new Screen(allFileLines, matExpressionResult, mathExpression);
    }

    public int calculateExpressionResult(String mathExpression){

        List<String> mathExpressionList = Arrays.asList(mathExpression.split(" "));

        //Usuwanie elementów zawierających pusty string
        mathExpressionList = mathExpressionList.stream().filter(x -> !x.equals(""))
                .collect(Collectors.toList());

        //Usuwanie pierwszego elementu jeżeli rozpoczyna się od operatora matematycznego
        if(mathExpressionList.get(0).matches("[/*+-]")){
            mathExpressionList.remove(0);
        }

        //Zwraca wartość w przypadku gdy jest jeden objekt
        if(mathExpressionList.size()==1){
            return Integer.parseInt(mathExpressionList.get(0));
        }


        int res = this.calculateTwoNumbersResult(mathExpressionList.get(0), mathExpressionList.get(1),
                mathExpressionList.get(2));

        mathExpressionList = mathExpressionList.subList(3, mathExpressionList.size());

        List<String> newMathExpressionList = new ArrayList<>();
        newMathExpressionList.add(String.valueOf(res));
        newMathExpressionList.addAll(mathExpressionList);

        if(newMathExpressionList.size()<3){
            return res;
        }else {
            StringBuilder stringBuilder = new StringBuilder();

            for(String str: newMathExpressionList){
                if(str.matches("[/*-+]")){
                    stringBuilder.append(" "+str+" ");
                }else {
                    stringBuilder.append(str);
                }
            }

            return this.calculateExpressionResult(stringBuilder.toString());
        }

    }

    public int calculateTwoNumbersResult(String numberOne, String operator, String numberTwo){

        int intNumberOne = Integer.parseInt(numberOne);
        int intNumberTwo = Integer.parseInt(numberTwo);

        if(operator.equals("*")){

            return intNumberOne*intNumberTwo;

        }else if(operator.equals("/")){

            return intNumberOne/intNumberTwo;

        }else if(operator.equals("+")){

            return intNumberOne+intNumberTwo;

        }else if(operator.equals("-")){

            return intNumberOne-intNumberTwo;

        }else {

            return 0;
        }

    }

}
