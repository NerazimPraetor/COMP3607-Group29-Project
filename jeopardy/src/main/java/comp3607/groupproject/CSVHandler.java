package comp3607.groupproject;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import java.io.*;
import java.util.*;

public class CSVHandler implements InputHandler, OutputHandler{
    @Override
    public List<GameContent> parse(File f){
        List<GameContent> questions = new ArrayList<>();

        try(CSVReader reader = new CSVReader(new FileReader(f))){
            String[] row;

            reader.readNext();

            while ((row = reader.readNext()) != null){
                GameContent question = new GameContent(row[0], Integer.parseInt(row[1]), row[2], row[3], row[4], row[5], row[6], row[7].charAt(0));
                questions.add(question);
            }
        } catch(IOException | CsvValidationException e){
            e.printStackTrace();
        }

        return questions;
    }

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
