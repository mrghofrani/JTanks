package GameObjects.MiddlePart.Items;

import GameObjects.GameObject;
import GameObjects.MiddlePart.MiddlePart;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class CannonBulletCartridgeItem extends GameObject implements Item,MiddlePart {


    public CannonBulletCartridgeItem(int locationX,int locationY){
        this.locationX = locationX;
        this.locationY = locationY;
        this.IMAGE_PATH += "CannonBulletCartridgeItem.png";
        setImage();
    }


    @Override
    public void act() {
        // Do nothing just be in your place
    }
}
