public class Rogue extends Player {

    private Integer cost;
    private Integer currentEnergy;

    public Rogue(String name, Health health, Integer attackPoints, Integer defensePoints,Integer cost) {
        super(name, health, attackPoints, defensePoints);
        this.cost=cost;
        this.currentEnergy=100;
    }

    @Override
    public void levelUp() {
        super.levelUp();
        this.currentEnergy=100;
        this.attackPoints=this.attackPoints+3*this.level;
    }

    public void gameTick(){
        this.currentEnergy=Math.min(this.currentEnergy+10,100);
    }

    public void fanOfKnives(){ // still need to be completed!
        if (currentEnergy<cost){
            System.out.println("Error, not enough energy!");
        }
        else
        {
            this.currentEnergy=this.currentEnergy-this.cost;
        }
    }
}
