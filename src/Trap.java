import org.omg.PortableInterceptor.INACTIVE;

public class Trap extends Enemy {

    private Integer range;
    private Integer respawn;
    private Integer visibilityTime;
    private boolean hidden;

    public Trap(String name, Health health, Integer attackPoints, Integer defensePoints, Integer experienceValue, char tile, Integer range, Integer respawn, Integer visibilityTime) {
        super(name, health, attackPoints, defensePoints, experienceValue, tile);
        this.range=range;
        this.respawn=respawn;
        this.visibilityTime=visibilityTime;
        this.hidden=this.visibilityTime>0;
    }

    public void gameTick(){ //relocates each turn, respawns randomly

    }

    public  void showTrap(){
        hidden=false;
    }
}
