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
        this.ticksCount=0;
    }
    public Trap(Trap e,Point position){
        super(e,position);
        this.ticksCount=e.ticksCount;
        this.relocationTime=e.relocationTime;
        this.relocationRange=e.relocationRange;
        this.visibilityTime=e.visibilityTime;
        this.hidden=e.hidden;
        this.position=position;
    }

    public void gameTick(){ //relocates each turn, respawns randomly
        if (ticksCount.intValue()==relocationTime.intValue()){
            ticksCount=0;
            LinkedList<Point> optPoints=new LinkedList<>();
            for (int i=0;i<GameBoard.gameBoard.length;i=i+1){
                for (int j=0;j<GameBoard.gameBoard[i].length;j=j+1){
                    if (this.euclideanDistance(this.position,new Point(j,i))<2&GameBoard.gameBoard[i][j]=='.')
                        optPoints.addFirst(new Point(j,i));
                }
            }
            Random rnd =new Random();
            this.position=optPoints.get(rnd.nextInt(optPoints.size()));
            setChanged();
            notifyObservers();
        }
        else {
            ticksCount=ticksCount+1;
            if (this.euclideanDistance(this.position,GameBoard.player.position)<2) {
                GameBoard.combat(this, GameBoard.player);
            }
        }
        if (ticksCount<visibilityTime)
            GameBoard.gameBoard[position.y][position.x]=this.getTile();
        else GameBoard.gameBoard[position.y][position.x]='.';
    }

    @Override
    public String toString() {
        return super.toString()+" Relocation Range: "+relocationRange+" Relocation Time: "+relocationTime
                +" Visibility Time: "+visibilityTime;
    }
}
