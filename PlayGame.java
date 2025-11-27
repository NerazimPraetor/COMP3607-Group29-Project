import java.io.File;
import java.time.LocalDateTime;
import java.util.*;



public class PlayGame{

    private final GameManager manager;
    private final List <Player> players = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    private String playerID;
    private final String caseID; 
    private static int gameID = 001;
    private String activity;
    private LocalDateTime timestamp;
    private String category;
    private int questionValue;
    private String answerGiven;
    private String result;
    private int scoreAfterPlay;
    private final List<LogData> logs = new ArrayList<>();
    CSVOutputHandler output =new CSVOutputHandler();

    public PlayGame(List <GameContent> gameContent){ // assuming File input is handled or can be updated once that logic is properly implemented 
        
        this.manager = new GameManager(gameContent);
        this.playerID = "System";
        this.caseID = "Game" + Integer.toString(gameID);
        gameID ++;
        this.timestamp = LocalDateTime.now();
        this.activity = "Load File";
        this.category = "";
        this.questionValue = 0;
        this.answerGiven = " ";
        this.result = "Success";
        this.scoreAfterPlay = 0;


        LogData log = new LogData(caseID, playerID, activity, timestamp, category, questionValue , answerGiven, result, scoreAfterPlay);
        logs.add(log);
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

        System.out.println("Welcome to the Jeopardy Game!");

        this.activity = "Start Game";
        this.timestamp = LocalDateTime.now();
        this.result = "";
        LogData log = new LogData(caseID, playerID, activity, timestamp, category, questionValue , answerGiven, result, scoreAfterPlay);
        logs.add(log);

        System.out.println("Please enter the number of players:");
        int size = scanner.nextInt();
        scanner.nextLine();

        this.activity = "Select Player Count";
        this.timestamp = LocalDateTime.now();
        this.answerGiven = Integer.toString(size);
        this.result = "N/A";
        log = new LogData(caseID, playerID, activity, timestamp, category, questionValue , answerGiven, result, scoreAfterPlay);
        logs.add(log);

        for(int i = 0; i<size; i++){

            System.out.println("Please enter the name of a player:");
            String name = scanner.nextLine();
            Player p = new Player(name);
            addPlayer(p);

            playerID = name;
            this.activity = "Enter Player Name";
            this.timestamp = LocalDateTime.now(); 
            this.answerGiven = name;
            log = new LogData(caseID, playerID, activity, timestamp, category, questionValue , answerGiven, result, scoreAfterPlay);
            logs.add(log);

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
            String c = scanner.nextLine();
            System.out.println();

            playerID = p.getName();
            activity = "Select Category";
            timestamp = LocalDateTime.now();
            this.category = c;
            questionValue = 0;
            this.answerGiven = "";
            this.result = "";
            this.scoreAfterPlay = 0;

            LogData log = new LogData(caseID, playerID, activity, timestamp, category, questionValue , answerGiven, result, scoreAfterPlay);
            logs.add(log);


            System.out.println("Chosen category: " + c);
            manager.viewValues(category);
            System.out.println();

            System.out.println("Please select a value: "); 
            int value = scanner.nextInt(); 
            scanner.nextLine();
            System.out.println();

            playerID = p.getName();
            activity = "Select Question";
            timestamp = LocalDateTime.now();
            this.category = c;
            questionValue = value;
            this.answerGiven = "";
            this.result = "";
            this.scoreAfterPlay = 0;

            log = new LogData(caseID, playerID, activity, timestamp, category, questionValue , answerGiven, result, scoreAfterPlay);
            logs.add(log);

            GameContent question = manager.getQuestionInfo(category, value);

            System.out.println("Your question for " + value + " points: "); 
            System.out.println(question.getQuestion());
            System.out.println("Your options: ");
            System.out.println("Option A: " + question.getA());
            System.out.println("Option B: " + question.getB());
            System.out.println("Option C: " + question.getC());
            System.out.println("Option D: " + question.getD());
            System.out.println("Your choice, A, B, C or D. Which is it?");
            char answerAsChar = scanner.next().charAt(0);
            String answerAsString = "";
            
            switch(answerAsChar){

                case'A' -> answerAsString = question.getA();

                case'B' -> answerAsString = question.getB();
                
                case'C' -> answerAsString = question.getC();

                case'D' -> answerAsString = question.getD();

            }
            
            playerID = p.getName();
            activity = "Answer Question";
            timestamp = LocalDateTime.now();
            this.category = c;
            questionValue = value;
            this.answerGiven = answerAsString;
            

            if (question.getAnswer() == answerAsChar){
                System.out.println("Correct!! You have earned " + value + " points.");
                p.addPoints(value);
                manager.markAsAnswered(question, category);
                this.result = "Correct";
                this.scoreAfterPlay = p.getScore();
            }else{
                System.out.println("Sorry incorrect answer. You have lost " + value + " points.");
                p.removePoints(value);
                manager.markAsNotAnswered(question, category);
                this.result = "Incorrect";
                this.scoreAfterPlay = p.getScore();
            }

            log = new LogData(caseID, playerID, activity, timestamp, category, questionValue , answerGiven, result, scoreAfterPlay);
            logs.add(log);

    }

    public void logEvent(String caseID, String playerID, String activity, LocalDateTime timestamp, String category, int questionValue, String answerGiven, String result, int scoreAfterPlay){
        
        LogData log = new LogData(caseID, playerID, activity, timestamp, category, questionValue, answerGiven, result, scoreAfterPlay);
        logs.add(log);
    }

    public void generateEventLog(){

        playerID = "System";
        activity = "Generate Event Log";
        timestamp = LocalDateTime.now();
        this.category = "";
        questionValue = 0;
        this.answerGiven = "";
        this.result = "N/A";
        this.scoreAfterPlay = 0;

        File eventLogFile = new File("game_event_log.csv");
        output.write(logs, eventLogFile);
    }

    public void endGame(){

        System.out.println("Game over! Final scores:");

        for (Player p : players) {

            System.out.println(p.getName() + ": " + p.getScore() + " points");
        }

        playerID = "System";
        activity = "Exit Game";
        timestamp = LocalDateTime.now();
        this.category = "";
        questionValue = 0;
        this.answerGiven = "";
        this.result = "";
        this.scoreAfterPlay = 0;

        LogData log = new LogData(caseID, playerID, activity, timestamp, category, questionValue , answerGiven, result, scoreAfterPlay);
        logs.add(log);

        scanner.close();
    }

}