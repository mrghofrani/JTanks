package GameObjects.MiddlePart.Tank.EnemyTanks.EnemyTank1Pack;

import GameObjects.GameObject;
import GameObjects.MiddlePart.Tank.UserTank.PlayerTank;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.security.SecureRandom;
import java.util.Random;

public class EnemyTank1 extends GameObject {

    private int health = 3;
    private int speed = 5;
    private int turn = 0;
    private int MOVEMENT_STATES = 9;
    private EnemyTank1Gun gun;
    private double angle;
    private double lastAngle;
    private int LOCATION_X;
    private int LOCATION_Y;
    public EnemyTank1(int locationX,int locationY){
        this.locationX = 200;
        this.locationY = 200;
        this.IMAGE_PATH += "EnemyTank1.png";
        setImage();
        gun = new EnemyTank1Gun(locationX + image.getWidth()/2,locationY + image.getHeight()/2);

    }


    @Override
    public void doRendering(Graphics2D g2d, int XOffset, int YOffset) {
        act();
        AffineTransform at = new AffineTransform();
        at.setToTranslation(locationX + XOffset + image.getWidth()/2,locationY + YOffset + image.getHeight()/2);
        at.rotate(angle);
//        at.translate(-500,-30);

        g2d.drawImage(image,locationX + XOffset,locationY + YOffset,null);
        gun.doRendering(g2d,locationX + XOffset,locationY + YOffset );
        g2d.setColor(Color.GREEN);
        g2d.fillOval(locationX + XOffset,locationY + YOffset,10,10);
    }

    @Override
    public void act() {


        /*if(Math.hypot(locationX - PlayerTank.XLocation,locationY - PlayerTank.YLocation) < 50) {
            gun.act();
        }
        else if(Math.hypot(locationX - PlayerTank.XLocation,locationY - PlayerTank.YLocation) < 200) { // TODO: this part doesn't work
            angle = Math.atan2(locationY - PlayerTank.YLocation,locationX - PlayerTank.XLocation);
            gun.act();
            angle -= Math.PI;
            locationX += speed * Math.cos(angle);
            locationY += speed * Math.sin(angle);
            System.out.println(this.getClass().getName() + " line 42" + " angle is " + (angle/Math.PI) + "PI");
        } else{
            LOCATION_X = locationX;
            LOCATION_Y = locationY;
            locationX = (int) ( LOCATION_X + speed * Math.cos(2*Math.PI * new SecureRandom().nextDouble()) );
            locationY = (int) ( LOCATION_Y + speed * Math.sin(2*Math.PI * new SecureRandom().nextDouble()) );
        }*/
    }
}
