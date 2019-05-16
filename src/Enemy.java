public abstract class Enemy extends GameUnit {

    private Integer experienceValue;
    private char tile;

    public Enemy(String name, Health health, Integer attackPoints, Integer defensePoints,Integer experienceValue,char tile) {
        super(name, health, attackPoints, defensePoints);
        this.experienceValue=experienceValue;
        this.tile=tile;
    }

    public Integer getExperienceValue() {
        return experienceValue;
    }

    public void attack(GameUnit player){
        super.attack(this,player);
    }
}
