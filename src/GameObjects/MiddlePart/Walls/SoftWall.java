package GameObjects.MiddlePart.Walls;

import GameObjects.GameObject;
import GameObjects.MiddlePart.Explosive;
import GameObjects.MiddlePart.HardObject;
import GameObjects.MiddlePart.MiddlePart;
import GameBasis.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class SoftWall extends GameObject implements Explosive,MiddlePart,HardObject {
    private BattleField battleField;
    public SoftWall(BattleField battleField,int locationX ,int locationY){
        this.locationX = locationX;
        this.locationY = locationY;
        this.battleField = battleField;
        this.health = 30;
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
    public void explode(int value) {
//        System.out.println("here");
        if(health - value > 0)
            health -= value;
        else
            health = 0;
        if(health < 30 && health >= 20) {
            IMAGE_PATH = "files" + File.separator + "Images" + File.separator + "softWall_2.png";
            setImage();
        }else if(health < 20 && health >= 10) {
            IMAGE_PATH = "files" + File.separator + "Images" + File.separator + "softWall_3.png";
            setImage();
        }else if(health < 10 && health > 0) {
            IMAGE_PATH = "files" + File.separator + "Images" + File.separator + "softWall_4.png";
            setImage();
        }else {
           isDeleted = true;
        }
    }

    /**
     * just for decor
     */
    @Override
    public void stop() {
        // nothing
    }
}
