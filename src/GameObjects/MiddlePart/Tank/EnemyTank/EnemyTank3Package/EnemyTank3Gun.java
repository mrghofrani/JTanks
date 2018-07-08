package GameObjects.MiddlePart.Tank.EnemyTank.EnemyTank3Package;

import GameObjects.GameObject;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class EnemyTank3Gun extends GameObject {
    EnemyTank3Gun(int locationX,int locationY){
        this.locationX = locationX;
        this.locationY = locationY;
        this.IMAGE_PATH += "enemyTank1Gun.png";
    }

    @Override
    public void act() {

    }


}
