package GameObjects.MiddlePart.Tank.EnemyTank.EnemyTank3Package;

import GameObjects.GameObject;
import GameObjects.MiddlePart.Explosive;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class EnemyTank3 extends GameObject implements Explosive {

    private EnemyTank3Gun gun;

    public EnemyTank3(int locationX, int locationY) {
        this.locationX = locationX;
        this.locationY = locationY;
        this.IMAGE_PATH += "EnemyTank3.png";
        health = 1;
        setImage();

        gun = new EnemyTank3Gun(locationX + image.getWidth()/2, locationY + image.getHeight()/2);
    }

    @Override
    public void act() {

    }



    @Override
    public void fire() {

    }

    @Override
    public void rotateTank(boolean direction) {

    }

    @Override
    public void rotateGun() {

    }

    @Override
    public void promoteWeapon() {

    }

    @Override
    public void switchGun() {

    }

    @Override
    public void destruct() {

    }

    @Override
    public void fullDestruct() {

    }

    @Override
    public void reNewTank() {

    }

    @Override
    public void damage(double value) {
        if(health - value > 0)
            health -= value;
        else
            health = 0;
        if(health == 0)
            BattleField.clearScreen();
    }
}
