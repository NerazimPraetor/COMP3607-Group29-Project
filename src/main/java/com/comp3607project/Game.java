package com.comp3607project;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game implements Subject{

    private final GameManager manager;
    private final List <Player> players = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);
    private String playerID;
    private final String caseID; 
    private static int gameID = 0;
    
    private final InputHandlerFactory input = new InputHandlerFactory();
    private boolean quitGame = false;
    private final List<Observer> observers = new ArrayList<>();

    public Game(File f){ 
        
        gameID ++;
        List <GameContent> gameContent;

        InputHandler inputHandler =  input.getInputHandler(f) ;
        gameContent = inputHandler.parse(f);

        this.manager = new GameManager(gameContent);
        
        
        this.playerID = "System";
        this.caseID = String.format("Game%03d", gameID); 
    }

    public void startGame(){

        LogData log = new LogData(caseID, playerID, "Load File", LocalDateTime.now(), "", 0 , "", "Success", 0);
        notify(log);

        System.out.println("Welcome to the Jeopardy Game!");

        
        log = new LogData(caseID, playerID, "Start Game", LocalDateTime.now(), "", 0 , "", "", 0);
        notify(log);

        System.out.println("Please enter the number of players (Max players 4):");
        int size = scanner.nextInt();
        scanner.nextLine();

        if (size >= 5){
            System.out.println("Four is the maximum amount of players for this game.");
            return;
        }

        log = new LogData(caseID, playerID, "Select Player Count", LocalDateTime.now(), "", 0 , Integer.toString(size), "N/A", 0);
        notify(log);

        for(int i = 0; i<size; i++){

            System.out.println("Please enter the name of a player:");
            String name = scanner.nextLine();
            Player p = new Player(name);
            addPlayer(p);

            log = new LogData(caseID, playerID, "Enter Player Name", LocalDateTime.now(), "", 0 , name, "N/A", 0);
            notify(log);

        }

        System.out.println();
        System.out.println("Players added successfully");

        System.out.println();
        System.out.println("The first round begins.");

        while (quitGame == false && !manager.checkIfEmpty()){

            for (int playerIndex = 0; playerIndex < players.size(); playerIndex ++){

                Player player = players.get(playerIndex);

                System.out.println("Player: " + player.getName());
                playTurn(player);

                if(quitGame == true){
                break;
                }
            }   
        }
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
    

    public void playTurn(Player p){

        boolean empty = manager.checkIfEmpty();
        if (empty){
            System.out.println("Players have answered all questions.");
            endGame();
            return;
        }

        playerID = p.getName();

        manager.viewCategories();
        System.out.println("Please select a category or enter Q to end game: "); 
        String c = scanner.nextLine();
        System.out.println();

        if(c.equalsIgnoreCase("Q")){
            System.out.println("You have decided to quit.");
            endGame();
            return;
        }

        boolean vaildCategory = manager.checkValidCategory(c.trim().toLowerCase());

        if (!vaildCategory){

            System.out.println ("Invaild category. Turn skipped.\n");
            return; 
        }

        LogData log = new LogData(caseID, playerID, "Select Category", LocalDateTime.now(), c, 0 , "", "", 0);
        notify(log);


        System.out.println("Chosen category: " + c);
        manager.viewValues(c.trim().toLowerCase());
        System.out.println();

        System.out.println("Please select a value: "); 
        int value = scanner.nextInt(); 
        scanner.nextLine();
        System.out.println();

        log = new LogData(caseID, playerID, "Select Question", LocalDateTime.now(), c, value , "", "", 0);
        notify(log);

        GameContent question = manager.getQuestionInfo(c.trim().toLowerCase(), value);

        if (question == null) {

            System.out.println("Invalid category or value. Turn skipped.");
            return;
        }

        System.out.println("Your question for " + value + " points: "); 
        System.out.println(question.getQuestion());

        System.out.println("Your options: ");
        System.out.println("Option A: " + question.getA());
        System.out.println("Option B: " + question.getB());
        System.out.println("Option C: " + question.getC());
        System.out.println("Option D: " + question.getD());
        System.out.println("Enter your choice: A, B, C or D. Or enter Q to end game.");
        char answerAsChar = scanner.next().charAt(0);
        scanner.nextLine();

        String answerAsString = manager.getAnswerAsString(answerAsChar, question);

        if(Character.toUpperCase(answerAsChar) == 'Q'){
            System.out.println("You have decided to quit.");
            endGame();
            return;
        }
            
        String result = manager.getResult(question, answerAsChar, value, c, p);
    
        log = new LogData(caseID, playerID, "Answer Question", LocalDateTime.now(), c, value , answerAsString, result, p.getScore());
        notify(log);

    }
    

    public void endGame(){

        System.out.println("Game over! Final scores:");

        for (Player p : players) {

            System.out.println(p.getName() + ": " + p.getScore() + " points");
        }

        LogData log = new LogData(caseID, "System", "Exit Game", LocalDateTime.now(), "", 0 , "", "", 0);
        notify(log);

        quitGame = true;
        scanner.close();

        for(Observer o:observers){
            if(o instanceof GameLogger){

                ((GameLogger) o).setFinalPlayers(players);
                ((GameLogger) o).setAnsweredQuestions(manager.getAnsweredQuestions());
                ((GameLogger) o).generateEventLog(caseID);
                ((GameLogger) o).generateReport();

            }
        }
    }

    public int getGameID(){
        return gameID;
    }

    //Subject methods

    @Override
    public void addObserver(Observer o){

        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o){
        
        observers.remove(o);
    }

    @Override
    public void notify(LogData log){

        for(Observer o:observers){
            o.update(log);
        }
    }

}
