package com.comp3607project;

import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import java.io.*;
import java.util.*;

public class CSVOutputHandler implements OutputHandler{
    @Override
    public void write(List<LogData> entries, File f){
        try(CSVWriter writer = new CSVWriter(new FileWriter(f))){
            String[] headers = {"Case_ID", "Player_ID", "Activity", "Timestamp", "Category", "Question_Value", "Answer_Given", "Result", "Score_After_Play"};
            writer.writeNext(headers);

            for(LogData e : entries){
                String row[] = {e.getCaseID(), e.getPlayerID(), e.getActivity(), e.getTimestamp().toString(), e.getCategory(), String.valueOf(e.getQuestionValue()), String.valueOf(e.getAnswerGiven()), e.getResult(), String.valueOf(e.getScoreAfterPlay())};

                writer.writeNext(row);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }   
}