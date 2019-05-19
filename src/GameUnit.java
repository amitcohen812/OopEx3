
import java.awt.*;
import java.util.Observable;
import java.util.Random;

public abstract class GameUnit extends Observable {
    protected String name;
    protected Health health;
    protected Integer attackPoints;
    protected Integer defensePoints;
    protected Point position;

    public GameUnit(String name,Health health,Integer attackPoints,Integer defensePoints){
        this.name=name;
        this.health=health;
        this.attackPoints=attackPoints;
        this.defensePoints=defensePoints;
    }

    public double euclideanDistance(Point first,Point second){
        return Math.sqrt((first.x-second.x)*(first.x-second.x)+(first.y-second.y)*(first.y-second.y));
    }

    protected int attack(GameUnit attacker,GameUnit defender){
        Random rnd=new Random();
        int attackPoints=rnd.nextInt(attacker.attackPoints+1);
        int defendPoints=rnd.nextInt(defender.defensePoints+1);
        int damage=attackPoints-defendPoints;
        if (damage>0)
            defender.health.setCurrentHealth(defender.health.getCurrentHealth()-damage);
        return damage;
    }
    protected void moveLeft(){
        if (position.x>0&&GameBoard.gameBoard[position.y][position.x-1]=='.') {
            clearSpot();
            this.position.x = this.position.x - 1;
            setChanged();
            notifyObservers();
        }
    }
    protected void moveRight(){
        if (GameBoard.gameBoard[position.y].length>position.x+1&&GameBoard.gameBoard[position.y][position.x+1]=='.') {
            clearSpot();
            this.position.x = this.position.x + 1;
            setChanged();
            notifyObservers();
        }
    }
    protected void moveUp(){
        if (position.y>0&&GameBoard.gameBoard[position.y-1][position.x]=='.') {
            clearSpot();
            this.position.y = this.position.y - 1;
            setChanged();
            notifyObservers();
        }
    }
    protected void moveDown(){
        if (position.y+1<GameBoard.gameBoard.length&&GameBoard.gameBoard[position.y+1][position.x]=='.') {
            clearSpot();
            this.position.y = this.position.y + 1;
            setChanged();
            notifyObservers();
        }
    }
    private void clearSpot(){
        GameBoard.gameBoard[this.position.y][this.position.x]='.';
    }
    @Override
    public String toString() {
        return "Name: "+name+" Health: "+health.getCurrentHealth()+"/"+health.getHealthPool()+" Attack points: "+attackPoints
                +" Defense points: "+defensePoints;
    }
}
