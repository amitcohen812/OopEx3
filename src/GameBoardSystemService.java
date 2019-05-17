public class GameBoardSystemService {

    public static void main(String [] args){
        GameBoard b;
        if (args.length==2){
            b=new GameBoard(args[0],args[1]);
        }
        else {
            b=new GameBoard(args[0]);
        }
        for (Enemy e : GameBoard.gameUnits) {
            b.addObserver(e);
        }


    }
}
