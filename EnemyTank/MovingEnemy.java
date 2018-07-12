package GameObjects.MiddlePart.Tank.EnemyTank;

import GameObjects.GameObject;
import GameObjects.MiddlePart.Explosive;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class MovingEnemy extends GameObject implements Explosive {

    public MovingEnemy(int locationX,int locationY){
        this.locationX = locationX;
        this.locationY = locationY;
        this.IMAGE_PATH += "movingEnemy.png";
        setImage();
    }

    @Override
    public void act() {

    }


    @Override
    public void damage(double value) {
        if(health - value > 0)
            health -= value;
        else
            health = 0;
        if(health == 0)
            BattleField.clearScreeen();
    }
}
