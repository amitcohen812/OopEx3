public class Rogue extends Player {

    private Integer cost;
    private Integer currentEnergy;
    private static final double range=2;

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

    public void castSpecialAbility(){
        if (currentEnergy<cost){ //should not print here!
            System.out.println("Error, not enough energy!");
        }
        else
        {
            this.currentEnergy=this.currentEnergy-this.cost;
            for(Enemy e: GameBoard.gameUnits){
                if (e.euclideanDistance(e.position,this.position)<range)
                    GameBoard.combat(this,e);
            }
        }
    }
}
