package GameUnits.Enemies;
import GameBoard.*;
import GameUnits.Health;

import java.awt.*;
import java.util.Random;

public class Monster extends Enemy {

    private Integer visionRange;

    public Monster(String name, Health health, Integer attackPoints, Integer defensePoints, Integer experienceValue, char tile, Integer visionRange) {
        super(name, health, attackPoints, defensePoints, experienceValue, tile);
        this.visionRange=visionRange;
    }
    public Monster(Monster e,Point position){
        super(e,position);
        this.position=position;
        this.visionRange=e.visionRange;
    }
    public void gameTick(){ //traversing around the board, chase if in range
        Point playerPosition= GameBoard.player.getPosition();
        if (this.euclideanDistance(this.position,playerPosition)<visionRange){
            int dx=this.position.x-playerPosition.x;
            int dy=this.position.y-playerPosition.y;
            if (Math.abs(dx)>Math.abs(dy)){
                if (dx>0)
                    super.moveLeft();
                else super.moveRight();
            }
            else {
                if (dy>0)super.moveUp();
                else super.moveDown();
            }
        }
        else{
            //Random rnd=new Random();
            //int index=rnd.nextInt(5);
            int index;
            try {
                index= GameBoardSystemService.nextNum(5);
            }
            catch (Exception e){Random rnd=new Random();index=rnd.nextInt(5);}//text file ran out of numbers
            switch (index){
                case 0:super.moveLeft();break;
                case 1: super.moveDown();break;
                case 2:super.moveRight();break;
                case 3:super.moveUp();break;
                //stay still - 4
            }
        }
    }

    @Override
    public String toString() {
        return super.toString()+" Vision range: "+visionRange;
    }
}
