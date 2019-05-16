
import java.util.LinkedList;

public class GameBoard{
    private String pathToBoards;
    private String isDeterministic;
    private char [][] gameBoard;
    private LinkedList<GameUnit> gameUnits;

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

    public void combat(Player attacker, Enemy defender){
       attacker.attack(defender);
       if (defender.health.getCurrentHealth()<=0)
           this.gameUnits.remove(defender);
    }
    public static void combat(Enemy attacker,Player defender){
        attacker.attack(defender);
        if (defender.health.getCurrentHealth()<=0){
            // put X on player and end the game.
        }
    }

}
