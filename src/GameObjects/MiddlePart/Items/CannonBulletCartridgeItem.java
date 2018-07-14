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

    public CannonBulletCartridgeItem(BattleField battleField,int locationX, int locationY){
        this.locationX = locationX;
        this.locationY = locationY;
        this.battleField = battleField;
        this.IMAGE_PATH += "CannonBulletCartridgeItem.png";
        setImage();
    }

    @Override
    public void act() {
        // Do nothing just be in your place
    }

    @Override
    public void dispose() {
       gift = 0;
    }

    @Override
    public int getGift() {
        return gift;
    }
}
