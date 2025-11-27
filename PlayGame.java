import java.util.*;

public class PlayGame{

    private List <GameContent> gameContent = new ArrayList<>();
    private GameManager manager;
    private List <Player> players = new ArrayList<>();

    public PlayGame(List <GameContent> gameContent){ // assuming File input is handled or can be updated once that logic is properly implemented 
        
        this.gameContent = gameContent;
        this.manager = new GameManager(gameContent);
    }

    public void play(Player p){

        if (players.size() < 4)
            players.add(p);
        else 
            System.out.println ("Maximum players reached for this game");
    }
    
    public void startGame(){

        for (Player p: players){

            manager.viewCategories();
            //manager.viewValues(category);
        }
    }

    public void endGame(){

    }

}