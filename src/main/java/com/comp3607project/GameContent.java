package com.comp3607project;

public class GameContent {
    private String category;
    private int value;
    private String question;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private char answer;

    public GameContent(String category, int value, String question, String optionA, String optionB, String optionC, String optionD, char answer) {
        this.category = category.toLowerCase();
        this.value = value;
        this.question = question;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.answer = answer;
    }

    public String getCategory() {
        return this.category;
    }

    public int getValue() {
        return this.value;
    }

    public String getQuestion() {
        return this.question;
    }

    public String getA() {
        return this.optionA;
    }

    public String getB() {
        return this.optionB;
    }

    public String getC() {
        return this.optionC;
    }

    public String getD() {
        return this.optionD;
    }

    public char getAnswer() {
        return this.answer;
    }

}
