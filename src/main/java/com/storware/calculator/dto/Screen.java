package com.storware.calculator.dto;


import java.util.List;
import java.util.Objects;

public class Screen {

    private List<String> inputFromFile;
    private int output;
    private String explanation;

    public Screen(List<String> inputFromFile, int output, String explanation) {
        this.inputFromFile = inputFromFile;
        this.output = output;
        this.explanation = explanation;
    }

    public List<String> getInputFromFile() {
        return inputFromFile;
    }

    public void setInputFromFile(List<String> inputFromFile) {
        this.inputFromFile = inputFromFile;
    }

    public int getOutput() {
        return output;
    }

    public void setOutput(int output) {
        this.output = output;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Screen)) return false;
        Screen screen = (Screen) o;
        return getOutput() == screen.getOutput() &&
                Objects.equals(getInputFromFile(), screen.getInputFromFile()) &&
                Objects.equals(getExplanation(), screen.getExplanation());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getInputFromFile(), getOutput(), getExplanation());
    }

    @Override
    public String toString() {
        return "Screen{" +
                "inputFromFile=" + inputFromFile +
                ", output=" + output +
                ", explanation='" + explanation + '\'' +
                '}';
    }
}
