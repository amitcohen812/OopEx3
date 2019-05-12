public abstract class Enemie extends GameUnit {

    private Integer experienceValue;
    private char tile;

    public Enemie(String name, Health health, Integer attackPoints, Integer defensePoints,Integer experienceValue,char tile) {
        super(name, health, attackPoints, defensePoints);
        this.experienceValue=experienceValue;
        this.tile=tile;
    }

}
