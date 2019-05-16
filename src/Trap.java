
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

    public void gameTick(){ //relocates each turn, respawns randomly
        if (ticksCount.intValue()==relocationTime.intValue()){
            ticksCount=0;
            //find all free spots and relocate
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
