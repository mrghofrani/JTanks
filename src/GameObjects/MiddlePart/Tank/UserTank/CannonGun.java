package GameObjects.MiddlePart.Tank.UserTank;

import GameBasis.BattleField;
import GameObjects.MiddlePart.Tank.Bullet.Cannon1;

import javax.sound.sampled.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;

public class CannonGun extends PlayerGun {

    private int numberOfBullet;
    private double angle;


    public CannonGun(int locationX,int locationY){
        System.out.println("gunlocationX " + locationX + " gunLocationY " + locationY);
        this.IMAGE_PATH += "playerCannonGun0.png";
        setImage();
        numberOfBullet = 1;
    }

    @Override
    public void shot(BattleField battleField,int locationX,int locationY, int mouseX, int mouseY) {
        if(numberOfBullet > 0) {
            battleField.add(new Cannon1(locationX,locationY ,mouseX, mouseY));
            numberOfBullet--;
        }else{
            outOfBulletSound();
        }
    }

    @Override
    public void aim(int locationX,int locationY,int mouseX, int mouseY) {
        angle = Math.atan2(mouseY - locationY,mouseX - locationX);
        System.out.println((angle/Math.PI));
    }


}
