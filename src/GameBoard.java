
import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.lang.ref.SoftReference;
import java.nio.file.Files;
import java.util.*;
import java.util.List;

public class GameBoard extends Observable{
    private String pathToBoards;
    private String isDeterministic;
    public static char [][] gameBoard;
    public static LinkedList<Enemy> gameUnits;
    public static Point playerPosition;
    public static Player player;
    private LinkedList<Enemy> possibleEnemies;

    public GameBoard(String pathToBoards,String isDeterministic){
        this.pathToBoards=pathToBoards;
        this.isDeterministic=isDeterministic;
        initEnemies();
        buildBoard();
        scanBoard();
    }
    public GameBoard(String pathToBoards){
        this.pathToBoards=pathToBoards;
        initEnemies();
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
    private void initEnemies(){
        possibleEnemies=new LinkedList<>();
        Monster lannisterSoldier=new Monster("Lannister Soldier",new Health(80,80),8,3,25,'s',3);
        Monster knight=new Monster("Lannister Knight",new Health(200,200),14,8,50,'k',4);
        Monster queensGuard=new Monster("Queen's Guard",new Health(400,400),20,15,100,'q',5);
        Monster wright=new Monster("Wright",new Health(600,600),30,15,100,'z',3);
        Monster bearWright=new Monster("Bear-Wright",new Health(1000,1000),75,30,250,'b',4);
        Monster giantWright=new Monster("Giant-Wright",new Health(1500,1500),100,40,500,'g',5);
        Monster whiteWalker=new Monster("White Walker",new Health(2000,2000),150,50,1000,'w',6);
        Monster theMountain =new Monster("The Mountain",new Health(1000,1000),60,25,500,'M',6);
        Monster queenCersei=new Monster("Queen Cersei",new Health(100,100),10,10,1000,'C',1);
        Monster knightKing=new Monster("Knight King",new Health(5000,5000),300,150,5000,'K',8);
        Trap bonusTrap=new Trap("Bonus Trap",new Health(1,1),1,1,250,'B',5,6,2);
        Trap queensTrap=new Trap("Queen's Trap",new Health(250,250),50,10,100,'Q',4,10,4);
        Trap deathTrap=new Trap("Death Trap",new Health(500,500),100,20,250,'D',6,10,3);
        possibleEnemies.addFirst(lannisterSoldier);
        possibleEnemies.addFirst(knight);
        possibleEnemies.addFirst(queenCersei);
        possibleEnemies.addFirst(queensGuard);
        possibleEnemies.addFirst(wright);
        possibleEnemies.addFirst(bearWright);
        possibleEnemies.addFirst(giantWright);
        possibleEnemies.addFirst(whiteWalker);
        possibleEnemies.addFirst(theMountain);
        possibleEnemies.addFirst(knightKing);
        possibleEnemies.addFirst(bonusTrap);
        possibleEnemies.addFirst(queensTrap);
        possibleEnemies.addFirst(deathTrap);

    }
    private void scanBoard(){ //iterates the whole game board
        for (int i=0;i<gameBoard.length;i=i+1){
            for (int j=0;j<gameBoard[i].length;j=j+1){
                char c=gameBoard[i][j];
                for (Enemy e:possibleEnemies){
                    if (e.getTile()==c)
                        if (e instanceof Monster){
                            gameUnits.addFirst(new Monster(e,new Point(i,j)));
                        }
                    else gameUnits.addFirst(new Trap(e,new Point(i,j)));
                }
            }
        }
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
            // puts X on player and end the game.
            gameBoard[defender.position.x][defender.position.y]='X';
            //endgame - needs to complete
        }
    }

    public void gameTick(){
        setChanged();
        notifyObservers();
    }




}
