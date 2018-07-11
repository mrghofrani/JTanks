package GameObjects.MiddlePart.Tank.Bullet;

import GameBasis.BattleField;
import GameObjects.GameObject;
import GameObjects.MiddlePart.Tank.UserTank.PlayerTank;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class EnemyTankCannon extends GameObject {
    private double angle;
    private int finalX;
    private int finalY;
    private int speed = 2;

    public EnemyTankCannon(int locationX, int locationY){
        this.locationX = locationX + 50;
        this.locationY = locationY + 50;
        finalX = PlayerTank.XLocation;
        finalY = PlayerTank.YLocation;
        angle = Math.atan2(finalY - locationY,finalX - locationX);
        this.IMAGE_PATH += "enemyTankCannon.png";
        setImage();
        act();
    }

    @Override
    public void doRendering(Graphics2D g2d, int XOffset, int YOffset) {
        move();
        AffineTransform at = new AffineTransform();
        at.setToTranslation(locationX + XOffset ,locationY + YOffset);
        at.rotate(angle - Math.PI/2);
        g2d.drawImage(image,at,null);
    }

    @Override
    public void act() {
    }

    public void move(){
        locationX += speed * Math.cos(angle);
        locationY += speed * Math.sin(angle);
    }
}
