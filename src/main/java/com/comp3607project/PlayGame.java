package com.comp3607project;

import java.io.File;
import java.io.IOException;

import static com.comp3607project.RandomFileGenerator.pickFile;

public class PlayGame {
    public static void main(String[] args) {
        
        try {
            String dir = "C:\\Users\\shemi\\OOP2\\jeopardy\\src\\main\\resources";
            File file = pickFile(dir);
            Game play = new Game(file);

            GameLogger logger = new GameLogger();

            play.addObserver(logger);
            play.startGame();

            logger.viewLogs();
            
        } 
        catch (IOException e) {

            System.err.println("Error: " + e.getMessage());
        }

        


        
    }
}