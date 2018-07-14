package GameObjects.MiddlePart.Walls;

import GameObjects.GameObject;
import GameObjects.MiddlePart.HardObject;
import GameObjects.MiddlePart.MiddlePart;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class HardWall extends GameObject implements MiddlePart,HardObject {
    public HardWall(int locationX ,int locationY){
        this.locationX = locationX;
        this.locationY = locationY;
        this.IMAGE_PATH += "HardWall.png";
        setImage();
    }
    /**
     * This method is using to force an object to be act
     */
    @Override
    public void act() {
        // Do nothing just be as hard as possible
    }

    @Override
    public void stop() {
        // Nothing
    }
}
