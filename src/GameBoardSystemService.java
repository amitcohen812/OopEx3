import java.util.LinkedList;

public class GameBoardSystemService {

    public static void main(String [] args){
        GameBoard b;
        args=new String[1];
        args[0]="C:\\Users\\amitc\\Desktop\\Degree\\Second Semester\\oop\\OopEx3\\levels\\level 1.txt";
        if (args.length==2){
            b=new GameBoard(args[0],args[1]);
        }
        else {
            b=new GameBoard(args[0]);
        }
        for (Enemy e : GameBoard.gameUnits) {
            b.addObserver(e);
        }
        LinkedList<Player> possiblePlayers=b.getPossiblePlayers();
        for (Player player :possiblePlayers){
            System.out.println(player);
        }
        System.out.println("Enter a number to choose a player");
        while (!GameBoard.endTheGame){
            //play!
            b.gameTick();
        }

    }
}
