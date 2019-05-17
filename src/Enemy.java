
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public abstract class Enemy extends GameUnit implements Observer {

    private Integer experienceValue;
    private char tile;

    public Enemy(String name, Health health, Integer attackPoints, Integer defensePoints,Integer experienceValue,char tile) {
        super(name, health, attackPoints, defensePoints);
        this.experienceValue=experienceValue;
        this.tile=tile;
    }

    public Integer getExperienceValue() {
        return experienceValue;
    }

    public void attack(GameUnit player){
        super.attack(this,player);
    }

    public char getTile() {
        return tile;
    }
    public void gameTick(){

    }

    @Override
    public void update(Observable o, Object arg) {
        gameTick();

    }
    protected void moveLeft(){
        super.moveLeft();
        Point tempPos=position;
        tempPos.x=tempPos.x-1;
        if (tempPos==GameBoard.playerPosition){
            GameBoard.combat(this,GameBoard.player);
        }
    }
    protected void moveRight(){
       super.moveRight();
        Point tempPos=position;
        tempPos.x=tempPos.x+1;
        if (tempPos==GameBoard.playerPosition){
            GameBoard.combat(this,GameBoard.player);
        }
    }
    protected void moveUp(){
        super.moveUp();
        Point tempPos=position;
        tempPos.y=tempPos.y+1;
        if (tempPos==GameBoard.playerPosition){
            GameBoard.combat(this,GameBoard.player);
        }
    }
    protected void moveDown() {
        super.moveDown();
        Point tempPos=position;
        tempPos.y=tempPos.y-1;
        if (tempPos==GameBoard.playerPosition){
            GameBoard.combat(this,GameBoard.player);
        }
    }

}
