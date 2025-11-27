import java.time.LocalDateTime;
import java.util.*;



public class PlayGame{

    private final GameManager manager;
    private final List <Player> players = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    private String playerID;
    private String caseID; 
    private static int gameID = 001;
    private String activity;
    private LocalDateTime timestamp;
    private String result;

    public PlayGame(List <GameContent> gameContent){ // assuming File input is handled or can be updated once that logic is properly implemented 
        
        this.manager = new GameManager(gameContent);
        this.playerID = "System";
        this.caseID = "Game" + Integer.toString(gameID);
        gameID ++;
    }

    public void addPlayer(Player p){

        if (players.size() == 4 ){
            System.out.println ("Maximum players reached for this game");
            return;
        }

        for (Player pl : players){
            if (pl.getName().equalsIgnoreCase(p.getName())){
                System.out.println("This player already exists");
                return;
            }
        } 
        players.add(p);
    }
    
    public void startGame(){

        System.out.println("Please enter the number of players:");
        int size = scanner.nextInt();
        scanner.nextLine();

        for(int i = 0; i<size; i++){

            System.out.println("Please enter the name of a player:");
            String name = scanner.nextLine();
            Player p = new Player(name);
            addPlayer(p);
        }

        for (int playerIndex = 0; playerIndex < size; playerIndex ++){

            Player player = players.get(playerIndex);

            if (playerIndex == 0)
                System.out.println("First round begins with: " + player.getName());
            else
                System.out.println("Next round for: " + player.getName());

            playTurn(player);
        }
        
    }

    public void playTurn(Player p){

        boolean empty = manager.checkIfEmpty();
            if (empty){
                System.out.println("Players have answered all questions. The game will now end.");
                endGame();
                return;
            }

        manager.viewCategories();
            System.out.println("Please select a category: "); 
            String category = scanner.nextLine();
            System.out.println();

            System.out.println("Chosen category: " + category);
            manager.viewValues(category);
            System.out.println();

            System.out.println("Please select a value: "); 
            int value = scanner.nextInt(); 
            scanner.nextLine();
            System.out.println();

            GameContent question = manager.getQuestionInfo(category, value);

            System.out.println("Your question for " + value + " points: "); 
            System.out.println(question.getQuestion());
            System.out.println("Your options: ");
            System.out.println("Option A: " + question.getA());
            System.out.println("Option B: " + question.getB());
            System.out.println("Option C: " + question.getC());
            System.out.println("Option D: " + question.getD());
            System.out.println("Your choice, A, B, C or D. Which is it?");
            char answer = scanner.next().charAt(0);
            
            if (question.getAnswer() == answer){
                System.out.println("Correct!! You have earned " + value + " points.");
                p.addPoints(value);
                manager.markAsAnswered(question, category);
            }else{
                System.out.println("Sorry incorrect answer. You have lost " + value + " points.");
                p.removePoints(value);
                manager.markAsNotAnswered(question, category);
            }

    }

    public void endGame(){

        System.out.println("Game over! Final scores:");

        for (Player p : players) {

            System.out.println(p.getName() + ": " + p.getScore() + " points");
        }

        scanner.close();
    }

}