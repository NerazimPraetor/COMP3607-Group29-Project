import java.time.LocalDateTime;

public class LogData {
    private String caseID;
    private String playerID;
    private String activity;
    private LocalDateTime timestamp;
    private String category;
    private int questionValue;
    private String answerGiven;
    private String result;
    private int scoreAfterPlay;

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
