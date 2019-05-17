import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

public class Trap extends Enemy {

    private Integer relocationRange;
    private Integer relocationTime;
    private Integer visibilityTime; //assume <relocationTime
    private Integer ticksCount;
    private boolean hidden;

    public Trap(String name, Health health, Integer attackPoints, Integer defensePoints, Integer experienceValue, char tile, Integer relocationRange, Integer relocationTime, Integer visibilityTime) {
        super(name, health, attackPoints, defensePoints, experienceValue, tile);
        this.relocationRange=relocationRange;
        this.relocationTime=relocationTime;
        this.visibilityTime=visibilityTime;
        this.hidden=this.visibilityTime>0;
    }
    public Trap(Enemy e,Point position){
        super(e,position);
    }

    public void gameTick(){ //relocates each turn, respawns randomly
        if (ticksCount.intValue()==relocationTime.intValue()){
            ticksCount=0;
            LinkedList<Point> optPoints=new LinkedList<>();
            for (int i=0;i<GameBoard.gameBoard.length;i=i+1){
                for (int j=0;j<GameBoard.gameBoard[i].length;i=i+1){
                    if (this.euclideanDistance(this.position,new Point(i,j))<2)
                        optPoints.addFirst(new Point(i,j));
                }
            }
            Random rnd =new Random();
            this.position=optPoints.get(rnd.nextInt(optPoints.size()));
        }
        else {
            ticksCount=ticksCount+1;
            if (this.euclideanDistance(this.position,GameBoard.playerPosition)<2)
                GameBoard.combat(this,GameBoard.player);
        }
        if (ticksCount<visibilityTime)
            showTrap();
        else hidden=true;
    }

    public  void showTrap(){
        hidden=false;
    }
}
