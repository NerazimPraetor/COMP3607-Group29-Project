package  com.comp3607project;

import java.io.File;

public class InputHandlerFactory{

    public InputHandler getInputHandler(File f){

        String name = f.getName();
        int dot = name.lastIndexOf('.');

        if (dot == -1){
            System.out.println("No extension found");
        }

        if (name.substring(dot).equals(".csv")){

            return new CSVInputHandler();
        }

        if (name.substring(dot).equals(".json")){

            return new JSONHandler();
        }

        if (name.substring(dot).equals(".xml")){

            return new XMLHandler();
        }

        return null;
    }
}