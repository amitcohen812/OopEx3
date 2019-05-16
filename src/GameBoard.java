
import java.awt.*;
import java.util.LinkedList;

public class GameBoard{
    private String pathToBoards;
    private String isDeterministic;
    private static char [][] gameBoard;
    public static LinkedList<Enemy> gameUnits;
    public static Point playerPosition;
    public static Player player;

    public GameBoard(String pathToBoards,String isDeterministic){
        this.pathToBoards=pathToBoards;
        this.isDeterministic=isDeterministic;
        buildBoard();
        scanBoard();
    }
    public GameBoard(String pathToBoards){
        this.pathToBoards=pathToBoards;
        buildBoard();
        scanBoard();
    }

    private void buildBoard(){ //reads the file from the path and builds the board

    }

    private void distinguishChar(char c){ //checks what kind of slot it is, enemy, player etc..., updates gameunit list

    }
    private void scanBoard(){ //iterates the whole game board
        for (int i=0;i<gameBoard.length;i=i+1){
            for (int j=0;j<gameBoard[i].length;j=j+1){
                distinguishChar(gameBoard[i][j]);
            }
        }
    }
    public char[][] getGameBoard() {
        return gameBoard;
    }

    public static void combat(Player attacker, Enemy defender){
       attacker.attack(defender);
       if (defender.health.getCurrentHealth()<=0) {
           gameUnits.remove(defender);
           attacker.experience+=defender.getExperienceValue();
       }
    }
    public static void combat(Enemy attacker,Player defender){
        attacker.attack(defender);
        if (defender.health.getCurrentHealth()<=0){
            // put X on player and end the game.
            gameBoard[defender.position.x][defender.position.y]='X';
            //endgame - needs to complete
        }
    }
}
