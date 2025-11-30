package com.comp3607project;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ReportWriter {
    
    public void write(List <LogData> logs, File f, List <String> q, List <Player> p){

        try (FileWriter writer = new FileWriter(f)){

            writer.write("JEOPARDY PROGRAMMING GAME REPORT\n================================\n\n");

            String caseID = "Case ID: " + logs.getFirst().getCaseID() + "\n\n";

            writer.write(caseID);
            writer.write("Players:");

            for (Player pl : p){
            
                String playerName = pl.getName();
                writer.write(playerName);

                int pSize = p.size() - 1; 
                
                
                if (pSize < 1 ){

                    writer.write(",");
                }
            }

            writer.write("\n\nGamePlay Summary:");
            writer.write("-----------------\n");

            int turn = 1;
            int index = 0;

            for (LogData l : logs){

                if (!l.getPlayerID().equals("System")){

                    if (l.getActivity().equals("Answer Question")){

                        writer.write("Turn " + Integer.toString(turn) + ": " + l.getPlayerID() + "selected " + l.getCategory() + " for " + l.getQuestionValue() + " pts\n");
                        writer.write("Question: " + q.get(index));
                        String answerRow = "Answer: " + l.getAnswerGiven() + " - " + l.getResult();

                        if (l.getResult().equals("Correct")){
                            answerRow = answerRow + " (+" + Integer.toString(l.getQuestionValue()) + "pts)\n";
                        }
                        else if (l.getResult().equals("Incorrect")) {

                            answerRow = answerRow + " (-" + Integer.toString(l.getQuestionValue()) + "pts)\n";
                        }

                        writer.write(answerRow);
                        writer.write("Score After Turn: " + l.getPlayerID() + " = " + l.getScoreAfterPlay() + "\n\n");
                        
                        
                        index++;
                        turn++;
                    }  
                }
            } 
            writer.write("Final Scores: ");

            for (Player pl: p){

                writer.write(pl.getName() + ": " + Integer.toString(pl.getScore()) + "\n");
            }

        } catch (IOException e){

            System.out.println(e.getMessage());
        }

    }
    
}
