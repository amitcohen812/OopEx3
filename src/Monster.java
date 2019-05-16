import java.awt.*;

public class Monster extends Enemy {

    private Integer visionRange;

    public Monster(String name, Health health, Integer attackPoints, Integer defensePoints, Integer experienceValue, char tile,Integer visionRange) {
        super(name, health, attackPoints, defensePoints, experienceValue, tile);
        this.visionRange=visionRange;
    }

    public void gameTick(){ //traversing around the board, chase if in range
        Point playerPosition=GameBoard.playerPosition;
        if (this.euclideanDistance(this.position,playerPosition)<visionRange){
            int dx=this.position.x-playerPosition.x;
            int dy=this.position.y-playerPosition.y;
            if (Math.abs(dx)>Math.abs(dy)){
                if (dx>0)
                    this.position.x=this.position.x-1;
                else this.position.x=this.position.x-1;
            }
            else {
                if (dy>0)this.position.y=this.position.y+1;
                else this.position.y=this.position.y-1;
            }
        }
        else{
            //moveRandom
        }
    }


}
