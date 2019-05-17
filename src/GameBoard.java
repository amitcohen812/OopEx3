
import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.lang.ref.SoftReference;
import java.nio.file.Files;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class GameBoard{
    private String pathToBoards;
    private String isDeterministic;
    public static char [][] gameBoard;
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

    public  void buildBoard(){ //reads the file from the path and builds the board
        File file =new File(pathToBoards);
        List<String> lines;
        try {
            lines=Files.readAllLines(file.toPath());
            gameBoard=new char[lines.size()][lines.get(0).length()];
            for (int i=0;i<lines.size();i=i+1){
                for (int j=0;j<lines.get(i).length();j=j+1){
                    gameBoard[i][j]=lines.get(i).charAt(j);
                }
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void distinguishChar(LinkedList<Character> listOfCopm){ //checks what kind of slot it is, enemy, player etc..., updates gameunit list
        for (Character c: listOfCopm)
        {
            if (c!='.'&c!='#'&c!='@'){

            }
        }
    }
    private void scanBoard(){ //iterates the whole game board
        LinkedList<Character> listOfComp=new LinkedList<>();
        for (int i=0;i<gameBoard.length;i=i+1){
            for (int j=0;j<gameBoard[i].length;j=j+1){
                listOfComp.addFirst(gameBoard[i][j]);
            }
        }
        distinguishChar(listOfComp);
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
