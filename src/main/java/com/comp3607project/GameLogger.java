package com.comp3607project;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class GameLogger implements Observer{

    private final List<LogData> logs = new ArrayList<>();
    private final CSVOutputHandler output =new CSVOutputHandler(); 
    private final List <GameContent> questions = new ArrayList<>();
    private final List <Player> players = new ArrayList<>();
    private boolean eventFileCreated = false;
    private boolean reportFileCreated = false;
    
    @Override
    public void update(LogData log){

        this.logs.add(log);
    }

    public void setFinalPlayers(List <Player> p){

        players.addAll(p);
    }

    public void setAnsweredQuestions(List <GameContent> q){

        questions.addAll(q);
    }

    public void generateEventLog(String id){

        LogData log = new LogData( id, "System", "Generate Event Log",  LocalDateTime.now(), "", 0 , "", "N/A", 0);
        logs.add(log);
    

        File eventLogFile = new File("game_event_log.csv");
        output.write(logs, eventLogFile);

        if(eventLogFile.exists()){

            eventFileCreated = true;
            System.out.println("CVS Process Mining Log successfully generated.\n");
        }
       
    }

    public void viewLogs(){

        System.out.println("Process Mining Logs:");

        for (LogData log : logs){

            System.out.println ("CaseID: " + log.getCaseID() + "\nPlayerID: " + log.getPlayerID() + "\nActivity: " + log.getActivity() + "\nTimestamp: " + log.getTimestamp() + "\nCategory: " + log.getCategory() + "\nQuestion Value: " + log.getQuestionValue() + "\nAnswer Given: " + log.getAnswerGiven() + "\nResult: " + log.getResult() + "\nScore After Play: " + log.getScoreAfterPlay() +"\n");
        }
    }

    public boolean getIfEventFileExists(){

        return this.eventFileCreated;
    }

    public void generateReport(){

        ReportWriter report = new ReportWriter();

        File reportFile = new File("GameReport.txt");

        report.write(logs, reportFile, questions, players);

         if(reportFile.exists()){

            reportFileCreated = true;
            System.out.println("Report generated.\n");
        }
    }

    public boolean getIfReportFileExists(){

        return this.reportFileCreated;
    }
}
