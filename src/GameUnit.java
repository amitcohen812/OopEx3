
import java.awt.*;

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
        return Math.sqrt((first.x-second.x)+(first.y-second.y));
    }

}
