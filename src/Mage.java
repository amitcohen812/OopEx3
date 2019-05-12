import org.omg.CORBA.MARSHAL;

public class Mage extends Player {
    private Integer spellPower;
    private Integer manaPool;
    private Integer currentMana;
    private Integer cost;
    private Integer hitTimes;
    private Integer range;

    public  Mage(String name,Health health,Integer attackPoints,Integer defensePoints,Integer spellPower,Integer manaPool,Integer cost,Integer hitTimes,Integer range){
        super(name, health, attackPoints, defensePoints);
        this.spellPower=spellPower;
        this.manaPool=manaPool;
        this.currentMana=this.manaPool/4;
        this.cost=cost;
        this.hitTimes=hitTimes;
        this.range=range;
    }

    @Override
    public void levelUp() {
        super.levelUp();
        this.manaPool=this.manaPool+25*this.level;
        this.currentMana=Math.min(this.currentMana+this.manaPool/4,this.manaPool);
        this.spellPower=this.spellPower+10*this.level;
    }

    public void gameTick(){
        this.currentMana= Math.min(this.manaPool,this.currentMana+1);
    }

    public void blizzard(){ // still needs to be completed!
        if (this.currentMana<this.cost){
            System.out.println("Error, you don't have enough mana yet");
        }
        else {
            this.currentMana=this.currentMana-cost;
            int hits=0;
            while (hits<hitTimes){

            }
        }
    }
}
