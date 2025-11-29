package com.comp3607project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GameManager{
 
    private final Map <String , Map <Integer, GameContent>> gameContentsMap = new HashMap<>();; // maps category to a map that contains value as a key and a GameContent object(question, optons, correct answer)
    private List<GameContent> gameContents = new ArrayList<>();
    private final List<GameContent> answeredContents = new ArrayList<>();

    public GameManager(List<GameContent> gameContents){

        this.gameContents = gameContents; 
        addContents();
   }


   private void addContents(){
        
        for (GameContent content : gameContents){

            String category = content.getCategory();
            int value = content.getValue();
            Map <Integer, GameContent> valueMap;
            
            if (!gameContentsMap.containsKey(category)) {

                valueMap = new HashMap<>();
                gameContentsMap.put(category,valueMap);
                
            } else {

                valueMap = gameContentsMap.get(category);
            }

            if (!valueMap.containsKey(value)){

                valueMap.put(value, content);
                
            } else {
                System.out.println("Value, " + value + " exists for category, " + category + "\n");
            }
    
        }
        
   }

   public void viewCategories(){

        if (gameContentsMap == null || gameContentsMap.isEmpty()){
                System.out.println("No categories found\n");
                return;
            }

        for (Map.Entry<String , Map <Integer, GameContent>> entry : gameContentsMap.entrySet()){

            System.out.println("Categories: " + entry.getKey());
        }

    }

    public Set <String> getCategories(){

        if (gameContentsMap == null || gameContentsMap.isEmpty()){
                System.out.println("No categories found\n");
                return null;
        }

        return gameContentsMap.keySet();  
    }

    public void viewValues(String category){

        if(gameContentsMap.containsKey(category)){
            Map <Integer, GameContent> valueMap = gameContentsMap.get(category);

                if(valueMap != null){

                    List<Integer> sortedKeys = new ArrayList<>(valueMap.keySet());
                    Collections.sort(sortedKeys);

                    for(int key: sortedKeys){

                        System.out.println("Values: " + key);
                    }

                } else {

                    System.out.println("No values found for this category\n");
                }
               
        }
        else{
            System.out.println("Category does not exist\n");  

        }
    }

    public Set <Integer> getValues(String c){

        Map <Integer, GameContent> valueMap = gameContentsMap.get(c);

        if (valueMap == null || valueMap.isEmpty()){
                System.out.println("No values found for this category\n");
                return null;
        }

        return valueMap.keySet();
    }

    public GameContent getQuestionInfo(String category , int value){

        if(gameContentsMap.containsKey(category)){
            Map <Integer, GameContent> valueMap = gameContentsMap.get(category);

                if (valueMap != null){

                    if(valueMap.containsKey(value)){
                        GameContent questionInfo = valueMap.get(value);
                        return questionInfo;
                    }
                    System.out.println("Value entered is invalid\n");
                }
                else{
                    System.out.println("There are no values in this category\n");
                } 
        }

        System.out.println ("Category entered is invalid\n");
        return null;
    }

    public void markAsAnswered(GameContent q, String c){
        
        answeredContents.add(q);
        
        Map <Integer, GameContent> valueMap = gameContentsMap.get(c);
        valueMap.remove(q.getValue());

        if (valueMap.isEmpty()){

            gameContentsMap.remove(c);
        }
    }

    public boolean checkIfEmpty(){

        return gameContentsMap.isEmpty();
    }

    public List<GameContent> getAnsweredQuestions(){

        return this.answeredContents;
    }

}