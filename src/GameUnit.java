
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
        if (position.x>0&&GameBoard.gameBoard[position.y][position.x-1]=='.')
            this.position.x=this.position.x-1;
    }
    protected void moveRight(){
        if (GameBoard.gameBoard[position.y].length<position.x+1&&GameBoard.gameBoard[position.y][position.x+1]=='.')
            this.position.x=this.position.x+1;
    }
    protected void moveUp(){
        if (position.y>0&&GameBoard.gameBoard[position.y-1][position.x]=='.')
            this.position.y= this.position.y-1;
    }
    protected void moveDown(){
        if (position.y+1<GameBoard.gameBoard.length&&GameBoard.gameBoard[position.y+1][position.x]=='.')
            this.position.y=this.position.y+1;
    }

    @Override
    public String toString() {
        return "Name: "+name+" Health: "+health.getCurrentHealth()+"/"+health.getHealthPool()+" Attack points: "+attackPoints
                +" Defense points: "+defensePoints;
    }
}
