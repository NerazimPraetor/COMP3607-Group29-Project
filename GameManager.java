import java.util.*;

public class GameManager{
 
    private Map <String , Map <Integer, GameContent>> gameContentsMap = new HashMap<>();; // maps category to a map that contains value as a key and a GameContent object(question, optons, correct answer)
    private List<GameContent> gameContents = new ArrayList<>();
    private List<GameContent> notAnswerContents = new ArrayList<>();

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

        for(Map.Entry<Integer, GameContent> entry: valueMap.entrySet()){

            System.out.println("Values: " + entry.getKey());
        }

    }

    public GameContent getQuestionInfo(String category , int value){

        Map <Integer, GameContent> valueMap = gameContentsMap.get(category);
        GameContent questionInfo = valueMap.get(value);

        
        return questionInfo;
    }

    public void notAnswered(GameContent question){

        notAnswerContents.add(question);

    }



}

