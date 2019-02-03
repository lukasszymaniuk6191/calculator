package com.storware.calculator.components;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MathExpressionCreator {

    public String createMathExpression(List<String> fileAllLines) {

        StringBuilder mathExpression = new StringBuilder();

        for (String line : fileAllLines) {

            String convertedLine = this.convertSingleLineToMathExpression(line);

            if (convertedLine != null) {

                if (convertedLine.contains("app")) {
                    mathExpression.insert(0, convertedLine.replace("app", ""));
                } else {
                    mathExpression.append(convertedLine);
                }

            }
        }

        return mathExpression.toString();
    }

    public String convertSingleLineToMathExpression(String line) {

        String[] splitedLine = line.split(" ");


        if (splitedLine.length == 2 && splitedLine[0].matches("^[a-zA-Z]*$")
                && splitedLine[1].matches("[0-9]+")) {

            if (splitedLine[0].equals("add")) {

                return (" + " + splitedLine[1]);

            } else if (splitedLine[0].equals("subtract")) {

                return " - " + splitedLine[1];

            } else if (splitedLine[0].equals("multiply")) {

                return " * " + splitedLine[1];

            } else if (splitedLine[0].equals("divide")) {

                return " / " + splitedLine[1];

            } else if (splitedLine[0].equals("apply")) {

                return "app" + splitedLine[1];
            } else {
                return null;
            }

        }

        return null;

    }

}
