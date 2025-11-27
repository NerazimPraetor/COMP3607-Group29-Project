public class Player{
    private String name;
    private int score;

    public Player(String name){
        this.name = name;
        this.score = 0;
        System.out.println("Player " + this.name + " added to the game.");
    }

    public String getName() {
        return this.name;
    }

    public int getScore() {
        
        return this.score;
    }

    public void addPoints(int value) {
        
        this.score += value;
    }
}