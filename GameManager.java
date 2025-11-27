import java.util.*;

public class GameManager{
 
    private Map <String , Map <Integer, GameContent>> gameContentsMap = new HashMap<>();; // maps category to a map that contains value as a key and a GameContent object(question, optons, correct answer)
    private List<GameContent> gameContents = new ArrayList<>();
    private List<GameContent> notAnsweredContents = new ArrayList<>();
    private List<GameContent> answeredContents = new ArrayList<>();

    public GameManager(List<GameContent> gameContents){

        this.gameContents = gameContents; 
   }


   public void addContents(){
        
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
                System.out.println("Value, " + value + " exists for category, " + category);
            }
    
        }
        
   }

   public void viewCategories(){

        if (gameContentsMap == null || gameContentsMap.isEmpty()){
                System.out.println("No categories found");
                return;
            }

        for (Map.Entry<String , Map <Integer, GameContent>> entry : gameContentsMap.entrySet()){

            System.out.println("Categories: " + entry.getKey());
        }

    }

    public void viewValues(String category){

        Map <Integer, GameContent> valueMap = gameContentsMap.get(category);

        if(valueMap != null){

            for(Map.Entry<Integer, GameContent> entry: valueMap.entrySet()){

                System.out.println("Values: " + entry.getKey());
            }
            return;
        }

        System.out.println("No values found for this category");

    }

    public GameContent getQuestionInfo(String category , int value){

        Map <Integer, GameContent> valueMap = gameContentsMap.get(category);
        GameContent questionInfo = valueMap.get(value);

        
        return questionInfo;
    }

    public void markAsNotAnswered(GameContent q, String c){

        notAnsweredContents.add(q);
        Map <Integer, GameContent> valueMap = gameContentsMap.get(c);

        if (valueMap != null){

            valueMap.remove(q.getValue());
        }

        if (valueMap.isEmpty()){
            gameContentsMap.remove(c);
        }
    }

    public void markAsAnswered(GameContent q, String c){
        
        answeredContents.add(q);
        
        Map <Integer, GameContent> valueMap = gameContentsMap.get(c);

        if (valueMap != null){

            valueMap.remove(q.getValue());
        }

        if (valueMap.isEmpty()){
            gameContentsMap.remove(c);
        }
    }

    public boolean checkIfEmpty(){

        return gameContentsMap.isEmpty();
    }


}

