public class Monster extends Enemie {

    private Integer range;

    public Monster(String name, Health health, Integer attackPoints, Integer defensePoints, Integer experienceValue, char tile,Integer range) {
        super(name, health, attackPoints, defensePoints, experienceValue, tile);
        this.range=range;
    }

    public void gameTick(){ //traversing around the board, chase if in range

    }


}
