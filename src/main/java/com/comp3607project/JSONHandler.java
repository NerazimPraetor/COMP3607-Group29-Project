package com.comp3607project;

import java.io.*;
import java.util.*;
import javax.json.*;

public class JSONHandler implements InputHandler{
    @Override
    public List<GameContent> parse(File f){
        List<GameContent> questions = new ArrayList<>();
         try(
            InputStream i = new FileInputStream(f);
            JsonReader reader = Json.createReader(i)
         ){ 
            JsonArray arr = reader.readArray();

            for(JsonValue v : arr){
                JsonObject obj = v.asJsonObject();

                GameContent question = new GameContent(obj.getString("Category"), obj.getInt("Value"), obj.getString("Question"), obj.getJsonObject("Options").getString("A"), obj.getJsonObject("Options").getString("B"), obj.getJsonObject("Options").getString("C"), obj.getJsonObject("Options").getString("D"), obj.getString("CorrectAnswer").charAt(0));
                questions.add(question);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return questions;
    }
}
