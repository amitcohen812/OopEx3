import java.util.LinkedList;
import java.util.Scanner;

public class GameBoardSystemService{
    private static DeterministicMode dm;

    public static void main(String [] args){
        GameBoard b;
        //args=new String[2];
        //args[0]="C:\\Users\\amitc\\Desktop\\Degree\\Second Semester\\oop\\OopEx3\\levels";
        //args[1]="-D";
        if (args.length==2){
            b=new GameBoard(args[0],args[1]);
            buildDeterministic();
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
        player.addObserver(b);
        GameBoard.player.position=GameBoard.playerPosition;
        System.out.println("you chose: "+player);
        System.out.println(b);
        System.out.println("For you to know! w - move up, s - move down, a - move left, d - move right" +
                "e - cast special ability, q - quit your turn");
        while (!GameBoard.endTheGame&(dm==null||dm.hasNext())){
            //play!
            System.out.println("use w/a/s/d/q/e to play");
            char c=nextAction();
            boolean flag=true;
            switch (c){
                case 'w': player.moveUp();break;
                case 's':player.moveDown();break;
                case 'a':player.moveLeft();break;
                case 'd':player.moveRight();break;
                case 'e':
                    {
                        flag= player.castSpecialAbility();
                        if (!flag) System.out.println("Could not cast special ability!");
                        else player.gameTick();
                        break;
                    }
                default:
                    System.out.println("You didn't move"); break;
            }
            if (flag)
                b.gameTick();
            else System.out.println("nothing has changed, ability usage didn't work");
            System.out.println(b);
            System.out.println(player);
            //reader=new Scanner(System.in);
        }
    }
    public static void onLevelUp(){
        System.out.println("Player leveled up!");
    }
    public static void onEnemyAttack(int damage){
        if (damage<0)
            damage=0;
        System.out.println("An enemy attacked you with the damage of: "+damage);
    }
    public static void onPlayerAttack(int damage){
        if (damage<0)
            damage=0;
        System.out.println("You attacked an Enemy with the damage of :"+damage);
    }
    public static void buildDeterministic(){
        dm=new DeterministicMode();
    }

    public static char nextAction() {
        if (dm==null) {
            Scanner reader = new Scanner(System.in);
            return reader.next().charAt(0);
        }
        return dm.nextAction().charAt(0);
    }
}
