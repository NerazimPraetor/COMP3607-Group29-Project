package com.comp3607project;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class CSVInputHandler implements InputHandler{
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
}
