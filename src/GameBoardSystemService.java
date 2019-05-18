import java.util.LinkedList;
import java.util.Scanner;

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
        LinkedList<Player> possiblePlayers=b.getPossiblePlayers();
        int count=1;
        for (Player player :possiblePlayers){
            System.out.println(count+" "+player);
            count++;
        }
        System.out.println("Enter a number to choose a player");
        Scanner reader=new Scanner(System.in);
        int choice=reader.nextInt();
        GameBoard.player=possiblePlayers.get(choice-1);
        Player player=GameBoard.player;
        GameBoard.player.position=GameBoard.playerPosition;
        System.out.println("you chose: "+GameBoard.player);
        System.out.println(b);
        System.out.println("For you to know! w - move up, s - move down, a - move left, d - move right" +
                "e - cast special ability, q - quit your turn");
        while (!GameBoard.endTheGame){
            //play!
            System.out.println("use w/a/s/d/q/e to play");
            char c=reader.next().charAt(0);
            switch (c){
                case 'w': player.moveUp();break;
                case 's':player.moveDown();break;
                case 'a':player.moveLeft();break;
                case 'd':player.moveRight();break;
                case 'e': player.castSpecialAbility();break;
                default:
                    System.out.println("You didn't move"); break;
            }
            b.gameTick();
            System.out.println(b);
            System.out.println(player);
            reader=new Scanner(System.in);
        }
    }
}
