package GameObjects.MiddlePart.Items;

import GameBasis.BattleField;
import GameObjects.GameObject;
import GameObjects.MiddlePart.MiddlePart;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class CannonBulletCartridgeItem extends GameObject implements Item,MiddlePart {

    private int gift = 100;
    private BattleField battleField;
    /**
     * first initializing of canonbulletcartdige item
     * @param battleField
     * @param locationX
     * @param locationY
     */
    public CannonBulletCartridgeItem(BattleField battleField,int locationX, int locationY){
        this.locationX = locationX;
        this.locationY = locationY;
        this.battleField = battleField;
        this.IMAGE_PATH += "CannonBulletCartridgeItem.png";
        setImage();
    }
    /**
     * manage's act of this object
     */
    @Override
    public void act() {
        // Do nothing just be in your place
    }
    /**
     * manage deleting this object
     */
    @Override
    public void dispose() {
       isDeleted = true;
    }
    /**
     * @return number of canon bullets is adding to tank
     */
    @Override
    public int getGift() {
        return gift;
    }
}
