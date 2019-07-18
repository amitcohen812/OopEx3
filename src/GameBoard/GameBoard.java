package GameBoard;

import GameUnits.*;
import GameUnits.Enemies.Enemy;
import GameUnits.Enemies.Monster;
import GameUnits.Enemies.Trap;
import GameUnits.Players.Mage;
import GameUnits.Players.Player;
import GameUnits.Players.Rogue;
import GameUnits.Players.Warrior;

import java.awt.*;
import java.io.File;
import java.nio.file.Files;
import java.util.*;
import java.util.List;

public class GameBoard implements Observer{

    private String pathToBoards;
    public static String isDeterministic;
    public static char [][] gameBoard;
    public static LinkedList<Enemy> gameUnits;
    public static Point playerPosition;
    public static Player player;
    private LinkedList<Enemy> possibleEnemies;
    public static boolean endTheGame=false;
    private LinkedList<Player> possiblePlayers;
    private static int level;
    private static LinkedList<File> files;

    public GameBoard(String pathToBoards,String deterministic){
        this.pathToBoards=pathToBoards;
        isDeterministic=deterministic;
        level=0;
        initEnemies();
        initPlayers();
        buildBoardList();
        scanBoard();
    }
    public GameBoard(String pathToBoards){
        this.pathToBoards=pathToBoards;
        level=0;
        initEnemies();
        initPlayers();
        buildBoardList();
        scanBoard();
    }

    public static void buildBoard(File file){ //reads the file from the path and builds the board
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
    private void buildBoardList(){
        files=new LinkedList<>();
        File file =new File(pathToBoards);
        if (file.isDirectory()) {
            File[] listOfFiles = file.listFiles();
            try {
                for (File f : listOfFiles) {
                    if (f.isFile())
                        files.addLast(f);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            buildBoard(files.get(level));
        }
        else System.out.println("problem");
    }
    private void initPlayers(){
        possiblePlayers=new LinkedList<>();
        Warrior jonSnow=new Warrior(6,"Jon Snow",new Health(300,300),30,4);
        Warrior hound=new Warrior(4,"The Hound",new Health(400,400),20,6);
        Mage melisandre =new Mage("Melisandre",new Health(160,160),10,1,40,300,30,5,6);
        Mage thoros=new Mage("Thoros Of Myr",new Health(250,250),25,3,15,150,50,3,3);
        Rogue aryaStark=new Rogue("Arya Stark",new Health(150,150),40,2,20);
        Rogue bronn =new Rogue("Bronn",new Health(250,250),35,3,60);
        possiblePlayers.addFirst(jonSnow);
        possiblePlayers.addFirst(hound);
        possiblePlayers.addFirst(melisandre);
        possiblePlayers.addFirst(thoros);
        possiblePlayers.addFirst(aryaStark);
        possiblePlayers.addFirst(bronn);
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
        Trap bonusTrap=new Trap("Bonus GameUnits.Enemies.Trap",new Health(1,1),1,1,250,'B',5,6,2);
        Trap queensTrap=new Trap("Queen's GameUnits.Enemies.Trap",new Health(250,250),50,10,100,'Q',4,10,4);
        Trap deathTrap=new Trap("Death GameUnits.Enemies.Trap",new Health(500,500),100,20,250,'D',6,10,3);
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
    public void scanBoard(){ //iterates the whole game board
        gameUnits=new LinkedList<>();
        for (int i=0;i<gameBoard.length;i=i+1){
            for (int j=0;j<gameBoard[i].length;j=j+1){
                char c=gameBoard[i][j];
                for (Enemy e:possibleEnemies){
                    if (e.getTile()==c)
                        if (e instanceof Monster){
                            gameUnits.addFirst(new Monster((Monster) e,new Point(j,i)));
                            gameUnits.get(0).addObserver(this);
                        }
                    else{ gameUnits.addFirst(new Trap((Trap) e,new Point(j,i)));gameUnits.get(0).addObserver(this);}
                }
                if (c=='@'){
                    playerPosition=new Point(j,i);
                }
            }
        }
    }

    public static void combat(Player attacker, Enemy defender){
       attacker.attack(defender);
       if (defender.getHealth().getCurrentHealth()<=0) {
           gameUnits.remove(defender);
           attacker.setExperience(defender.getExperienceValue()+attacker.getExperience());
           gameBoard[defender.getPosition().y][defender.getPosition().x]='.';
           if (gameUnits.size()==0){
               if (level+1==files.size()) {
                   endTheGame = true;
                   GameBoardSystemService.onWinning();
               }
                else{ level++; buildBoard(files.get(level));}
           }

       }
    }
    public static void passByOrder(){
        if (level+1==files.size()) {
            endTheGame = true;
            GameBoardSystemService.onWinning();
        }
        else{ level++; buildBoard(files.get(level));}
    }
    public static void combat(Enemy attacker,Player defender){
        attacker.attack(defender);
        if (defender.getHealth().getCurrentHealth()<=0){
            gameBoard[defender.getPosition().y][defender.getPosition().x]='X';
            endTheGame=true;
            GameBoardSystemService.onLosing();
        }
    }

    public void gameTick(){
        for (Enemy enemy :gameUnits)
            enemy.gameTick();
    }

    public String getIsDeterministic() {
        return isDeterministic;
    }
    public LinkedList<Player> getPossiblePlayers(){
        return this.possiblePlayers;
    }

    @Override
    public String toString() {
        String output="";
        for (int i=0;i<gameBoard.length;i=i+1){
            for (int j=0;j<gameBoard[i].length;j=j+1){
                output+=gameBoard[i][j];
            }
            output+="\n";
        }
        return output;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (gameUnits.contains(o))
            gameBoard[((GameUnit) o).getPosition().y][((GameUnit) o).getPosition().x]=((Enemy) o).getTile();
        else{
            player.setPosition(((GameUnit) o).getPosition());
            gameBoard[player.getPosition().y][player.getPosition().x]='@';
        }
    }
}
