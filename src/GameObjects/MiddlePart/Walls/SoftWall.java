package GameObjects.MiddlePart.Walls;

import GameObjects.GameObject;
import GameObjects.MiddlePart.Explosive;
import GameObjects.MiddlePart.MiddlePart;
import GameBasis.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class SoftWall extends GameObject implements Explosive,MiddlePart{
    public SoftWall(int locationX ,int locationY){
        this.locationX = locationX;
        this.locationY = locationY;
        this.health = 3;
        this.IMAGE_PATH += "SoftWall_1.png";
        setImage();
    }

    /**
     * This method is using to force an object to be act
     */
    @Override
    public void act() {
        // "Do nothing" this is your duty +)
    }

    /**
     * This method runs when
     * a explosive object is
     * going to be damaged
     */
    @Override
    public void damage(double value) {
        if(health - value > 0)
            health -= value;
        else
            health = 0;
        if(health <= 3 && health > 2) {
            IMAGE_PATH.substring(0,IMAGE_PATH.lastIndexOf('/') + 1);
            IMAGE_PATH += "softWall_2.png";
        }
        else if(health <= 2 && health > 1) {
            IMAGE_PATH.substring(0,IMAGE_PATH.lastIndexOf('/') + 1);
            IMAGE_PATH += "softWall_3.png";
        }
        else if(health <=1 && health > 0) {
            IMAGE_PATH.substring(0,IMAGE_PATH.lastIndexOf('/') + 1);
            IMAGE_PATH += "softWall_4.png";
        }
        else if(health == 0) {
//            battle.clearScreen();
        }
    }
}
