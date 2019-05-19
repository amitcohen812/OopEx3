import java.awt.*;

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
    }

    public Player(String name, Health health, Integer attackPoints, Integer defensePoints) {
        super(name, health, attackPoints, defensePoints);
        this.level = 1;
        this.experience=0;
    }

    public void castSpecialAbility() {
    }

    public void attack(GameUnit enemy){
        super.attack(this,enemy);
    }

    @Override
    public String toString() {
        return super.toString()+" Experience: "+experience+" Level: "+level;
    }

    public void gameTick(){}
    @Override
    protected void moveDown() {
        super.moveDown();
        gameTick();
    }

    @Override
    protected void moveLeft() {
        super.moveLeft();
        gameTick();
    }

    @Override
    protected void moveRight() {
        super.moveRight();
        gameTick();
    }

    @Override
    protected void moveUp() {
        super.moveUp();
        gameTick();
    }
}