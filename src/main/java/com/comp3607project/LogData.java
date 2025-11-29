package com.comp3607project;

import java.time.LocalDateTime;

public class LogData {
    private final String caseID;
    private final String playerID;
    private final String activity;
    private final LocalDateTime timestamp;
    private final String category;
    private final int questionValue;
    private final String answerGiven;
    private final String result;
    private final int scoreAfterPlay;

    public LogData(String caseID, String playerID, String activity, LocalDateTime timestamp, String category, int questionValue, String answerGiven, String result, int scoreAfterPlay){
        this.caseID = caseID;
        this.playerID = playerID;
        this.activity = activity;
        this.timestamp = timestamp;
        this.category = category;
        this.questionValue = questionValue;
        this.answerGiven = answerGiven;
        this.result = result;
        this.scoreAfterPlay = scoreAfterPlay;
    }

    public String getCaseID(){
        return this.caseID;
    }
    public String getPlayerID(){
        return this.playerID;
    }
    public String getActivity() {
        return this.activity;
    }
    public LocalDateTime getTimestamp() {
        return this.timestamp;
    }
    public String getCategory() {
        return this.category;
    }
    public int getQuestionValue() {
        return this.questionValue;
    }
    public String getAnswerGiven() {
        return this.answerGiven;
    }
    public String getResult() {
        return this.result;
    }
    public int getScoreAfterPlay() {
        return this.scoreAfterPlay;
    }
}