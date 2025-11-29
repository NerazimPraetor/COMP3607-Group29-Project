package com.comp3607project;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class GameLogger implements Observer{

    private final List<LogData> logs = new ArrayList<>();
    private final CSVOutputHandler output =new CSVOutputHandler();
    private  int turn;
    private  String question;
    private boolean fileCreated = false;
    
    @Override
    public void update(LogData log, int turn, String question){

        this.logs.add(log);

        if (turn != 0){
            this.turn = turn;
        }

        if(!question.equals("")){
            this.question = question;
        }

    }

    public void generateEventLog(String id){

        LogData log = new LogData( id, "System", "Generate Event Log",  LocalDateTime.now(), "", 0 , "", "N/A", 0);
        logs.add(log);
    

        File eventLogFile = new File("game_event_log.csv");
        output.write(logs, eventLogFile);

        if(eventLogFile.exists()){

            fileCreated = true;
             System.out.println("CVS Process Mining Log successfully generated.\n");
        }
       
    }

    public void viewLogs(){

        System.out.println("Process Mining Logs:");

        for (LogData log : logs){

            System.out.println ("CaseID: " + log.getCaseID() + "\nPlayerID: " + log.getPlayerID() + "\nActivity: " + log.getActivity() + "\nTimestamp: " + log.getTimestamp() + "\nCategory: " + log.getCategory() + "\nQuestion Value: " + log.getQuestionValue() + "\nAnswer Given: " + log.getAnswerGiven() + "\nResult: " + log.getResult() + "\nScore After Play: " + log.getScoreAfterPlay() +"\n");
        }
    }

    public boolean fileCreated(){

        return this.fileCreated;
    }
}
