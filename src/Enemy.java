
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
    public Enemy(Enemy enemy,Point position){
        super(enemy.name,enemy.health,enemy.attackPoints,enemy.defensePoints);
        this.experienceValue=enemy.experienceValue;
        this.tile=enemy.getTile();
        this.position=position;
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
        Point tempPos=new Point(position.x,position.y);
        tempPos.x=tempPos.x-1;
        if (tempPos==GameBoard.playerPosition){
            GameBoard.combat(this,GameBoard.player);
        }
    }
    protected void moveRight(){
       super.moveRight();
        Point tempPos=new Point(position.x,position.y);
        tempPos.x=tempPos.x+1;
        if (tempPos==GameBoard.playerPosition){
            GameBoard.combat(this,GameBoard.player);
        }
    }
    protected void moveUp(){
        super.moveUp();
        Point tempPos=new Point(position.x,position.y);
        tempPos.y=tempPos.y-1;
        if (tempPos==GameBoard.playerPosition){
            GameBoard.combat(this,GameBoard.player);
        }
    }
    protected void moveDown() {
        super.moveDown();
        Point tempPos=new Point(position.x,position.y);
        tempPos.y=tempPos.y+1;
        if (tempPos==GameBoard.playerPosition){
            GameBoard.combat(this,GameBoard.player);
        }
    }

    @Override
    public String toString() {
        return super.toString()+" Experience value: "+experienceValue+" Tile: "+tile;
    }
}
