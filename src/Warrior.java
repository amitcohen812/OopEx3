

public class Warrior extends Player {
    private Integer cooldown;
    private Integer remaining;

    public Warrior(Integer cooldown,String name,Health health,Integer attackPoints,Integer defensePoints){
        super(name, health, attackPoints, defensePoints);
        this.cooldown=cooldown;
        this.remaining=cooldown;
    }

    public void castSpecialAbility(){
        if (this.remaining>0) //should not print here !
            System.out.println("error, still on cooldown!");
        else {
            if (this.health.getCurrentHealth() + 2 * this.defensePoints <= this.health.getHealthPool()) {
                this.health.setCurrentHealth(this.health.getCurrentHealth() + 2 * this.defensePoints);
            } else this.health.setCurrentHealth(this.health.getHealthPool());
        }
    }

    @Override
    public void levelUp() {
        super.levelUp();
        this.remaining=0;
        this.health.setHealthPool(this.health.getHealthPool()+5*this.level);
        this.defensePoints=this.defensePoints+this.level;
    }

    public void gameTick(){
        this.remaining=this.remaining-1;
    }

    @Override
    public String toString() {
        return super.toString()+" Cooldown: "+cooldown+" Remaining: "+remaining;
    }
}
