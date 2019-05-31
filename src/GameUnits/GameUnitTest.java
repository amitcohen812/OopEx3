package GameUnits;

import GameUnits.Enemies.Monster;
import GameUnits.Players.Warrior;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GameUnitTest {
    private  Warrior jonTest=new Warrior(6,"Jon Snow",new Health(300,300),30,4);
    Monster lannisterSoldierTest=new Monster("Lannister Soldier",new Health(80,80),8,3,25,'s',3);

    @Test
    public void euclideanDistance() {
        double range=jonTest.euclideanDistance(new Point(0,0),new Point(3,3));
        int i=(int)range;
        assertEquals(i,4);
    }

    @Test
     public void attack(){
        int num= jonTest.attack(jonTest,lannisterSoldierTest);
        assertTrue(num>=0);
     }

     @Test
     public void testSetPos(){
        jonTest.setPosition(new Point(2,3));
        assertTrue(jonTest.position.x==2&jonTest.position.y==3);
     }


}