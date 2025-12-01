package com.comp3607project;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ReportWriter {
    
    public void write(List <LogData> logs, File f, List <GameContent> q, List <Player> p){

        try (FileWriter writer = new FileWriter(f)){

            writer.write("JEOPARDY PROGRAMMING GAME REPORT\n================================\n\n");

            String caseID = "Case ID: " + logs.getFirst().getCaseID() + "\n\n";

            writer.write(caseID);
            writer.write("Players:");

            int count = 0;
            for (Player pl : p){
            
                String playerName = pl.getName();
                writer.write(playerName);

                
                if (count < p.size() - 1 ){

                    writer.write(",");
                }

                count++;
            }

            writer.write("\n\nGamePlay Summary:\n");
            writer.write("-----------------\n");

            int turn = 1;
            int index = 0;

            for (LogData l : logs){

                if (!l.getPlayerID().equals("System")){

                    if (l.getActivity().equals("Answer Question")){

                        writer.write("Turn " + Integer.toString(turn) + ": " + l.getPlayerID() + " selected " + l.getCategory() + " for " + l.getQuestionValue() + " pts\n");
                        writer.write("Question: " + q.get(index).getQuestion());
                        String answerRow = "\nAnswer: " + l.getAnswerGiven() + " - " + l.getResult();

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
            writer.write("Final Scores: \n");

            for (Player pl: p){

                writer.write(pl.getName() + ": " + Integer.toString(pl.getScore()) + "\n");
            }

        } catch (IOException e){

            System.out.println(e.getMessage());
        }

    }
    
}
