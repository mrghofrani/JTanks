package GameObjects.MiddlePart.Tank.EnemyTank.EnemyTank2Package;

import GameObjects.GameObject;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class EnemyTank2Gun extends GameObject {
    EnemyTank2Gun(int locationX,int locationY){
        this.locationX = locationX;
        this.locationY = locationY;
        this.IMAGE_PATH += "enemyTank2Gun.png";
    }

    @Override
    public void act() {

    }


}
