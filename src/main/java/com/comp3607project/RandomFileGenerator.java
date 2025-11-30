package com.comp3607project;

import java.io.File;
import java.io.IOException;
import java.util.Random;


public class RandomFileGenerator {

    public static File pickFile(String dirPath) throws IOException{

        File dir = new File(dirPath);

        if (!dir.exists() || !dir.isDirectory()) {

            throw new IOException("Invalid directory: " + dirPath);
        }

        File[] files = dir.listFiles(File:: isFile);

        if(files == null || files.length == 0){
            throw new IOException ("No files found in directory: " + dirPath);
        }

        Random rand = new Random();
        File f = files[rand.nextInt(files.length)];

        return f;
    }

}
