package GameObjects.MiddlePart.Tank.EnemyTanks.EnemyTank1Pack;

import GameObjects.GameObject;
import GameObjects.MiddlePart.Tank.Bullet.EnemyTankCannon;
import GameObjects.MiddlePart.Tank.UserTank.PlayerTank;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class EnemyTank1Gun extends GameObject {

    private double angle;

    public EnemyTank1Gun(int locationX, int locationY) {
       this.locationX = locationX;
       this.locationY = locationY;
       this.IMAGE_PATH += "EnemyTank1Gun.png";
       setImage();
    }


    public void doRendering(Graphics2D g2d,int locationX,int locationY){
        this.locationX = locationX;
        this.locationY = locationY;
        angle = Math.atan2(this.locationY - PlayerTank.YLocation, this.locationX - PlayerTank.XLocation);
        AffineTransform at = new AffineTransform();
        at.setToTranslation(this.locationX + 50 ,this.locationY + image.getHeight()/2 );
        g2d.setColor(Color.WHITE);
        g2d.fillOval(this.locationX ,this.locationY ,5,5);
        at.rotate(angle - Math.PI);
        at.translate(0,0);
        g2d.drawImage(image,at,null);
    }

    @Override
    public void act() {
        new EnemyTankCannon(locationX,locationY);
    }
}
