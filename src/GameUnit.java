
import java.awt.*;
import java.util.Random;

public abstract class GameUnit {
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

    protected void attack(GameUnit attacker,GameUnit defender){
        Random rnd=new Random();
        int attackPoints=rnd.nextInt(attacker.attackPoints+1);
        int defendPoints=rnd.nextInt(defender.defensePoints+1);
        int damage=attackPoints-defendPoints;
        if (damage>0)
            defender.health.setCurrentHealth(defender.health.getCurrentHealth()-damage);
    }
    protected void moveLeft(){
        if (GameBoard.gameBoard[position.x-1][position.y]=='.')
            this.position.x=this.position.x-1;
    }
    protected void moveRight(){
        if (GameBoard.gameBoard[position.x+1][position.y]=='.')
            this.position.x=this.position.x+1;
    }
    protected void moveUp(){
        if (GameBoard.gameBoard[position.x][position.y+1]=='.')
            this.position.y= this.position.y+1;
    }
    protected void moveDown(){
        if (GameBoard.gameBoard[position.x][position.y-1]=='.')
            this.position.y=this.position.y-1;
    }
}
