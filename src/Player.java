import java.awt.*;
import java.util.LinkedList;

public abstract class Player extends GameUnit {

    protected Integer experience;
    protected Integer level;

    public void levelUp() {
        this.experience = this.experience - 50 * this.level;
        this.level = this.level + 1;
        this.health.setHealthPool(this.health.getHealthPool() + 10 * this.level);
        this.health.setCurrentHealth(this.health.getHealthPool());
        this.attackPoints = this.attackPoints + 5 * level;
        this.defensePoints = this.defensePoints + 2 * level;
        GameBoardSystemService.onLevelUp();
    }

    public Player(String name, Health health, Integer attackPoints, Integer defensePoints) {
        super(name, health, attackPoints, defensePoints);
        this.level = 1;
        this.experience=0;
    }

    public boolean castSpecialAbility() {
        return false; // trash value
    }

    public void attack(GameUnit enemy){
        int damage=super.attack(this,enemy);
        GameBoardSystemService.onPlayerAttack(damage);
    }

    @Override
    public String toString() {
        return super.toString()+" Experience: "+experience+" Level: "+level;
    }

    public void gameTick(){}
    @Override
    protected void moveDown() {
        super.moveDown();
        Point tempPos=new Point(position.x,position.y);
        tempPos.y=tempPos.y+1;
        Enemy toAttack=new Trap("temp",new Health(100,100),0,0,0,'t',0,0,0);
        for (Enemy enemy :GameBoard.gameUnits) {
            if (enemy.position.x==tempPos.x&enemy.position.y==tempPos.y)
                toAttack=enemy;
        }
        if (!toAttack.name.equals("temp"))
            GameBoard.combat(this,toAttack);
        gameTick();
    }

    @Override
    protected void moveLeft() {
        super.moveLeft();
        Point tempPos=new Point(position.x,position.y);
        tempPos.x=tempPos.x-1;
        Enemy toAttack=new Trap("temp",new Health(100,100),0,0,0,'t',0,0,0);
        for (Enemy enemy :GameBoard.gameUnits) {
            if (enemy.position.x==tempPos.x&enemy.position.y==tempPos.y)
                toAttack=enemy;
        }
        if (!toAttack.name.equals("temp"))
            GameBoard.combat(this,toAttack);
        gameTick();
    }

    @Override
    protected void moveRight() {
        super.moveRight();
        Point tempPos=new Point(position.x,position.y);
        tempPos.x=tempPos.x+1;
        Enemy toAttack=new Trap("temp",new Health(100,100),0,0,0,'t',0,0,0);
        for (Enemy enemy :GameBoard.gameUnits) {
            if (enemy.position.x==tempPos.x&enemy.position.y==tempPos.y)
                toAttack=enemy;
        }
        if (!toAttack.name.equals("temp"))
            GameBoard.combat(this,toAttack);
        gameTick();
    }

    @Override
    protected void moveUp() {
        super.moveUp();
        Point tempPos=new Point(position.x,position.y);
        tempPos.y=tempPos.y-1;
        Enemy toAttack=new Trap("temp",new Health(100,100),0,0,0,'t',0,0,0);
        for (Enemy enemy :GameBoard.gameUnits) {
            if (enemy.position.x==tempPos.x&enemy.position.y==tempPos.y)
                toAttack=enemy;
        }
        if (!toAttack.name.equals("temp"))
            GameBoard.combat(this,toAttack);
        gameTick();
    }
}