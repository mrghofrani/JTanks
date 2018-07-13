package GameObjects.MiddlePart.Tank.EnemyTanks;

import GameBasis.BattleField;
import GameObjects.GameObject;
import GameObjects.MiddlePart.Explosive;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public abstract class EnemyTankTemplate  extends GameObject implements Explosive{
    protected double angle;
    protected int speed;

    protected double gunAngle;
    protected String GUN_IMAGE = "files" + File.separator + "Images" + File.separator;
    protected BufferedImage gunImage;


    protected boolean isNear;
    protected BattleField battleField;
    protected Timer actionTimer;

    protected int health;

    protected void setGunImage(){
        File file = new File(GUN_IMAGE);
        try {
            gunImage = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void doRendering(Graphics2D g2d, int XOffset, int YOffset) {
        paintTank(g2d,XOffset,YOffset);
        paintGun(g2d,XOffset,YOffset);
    }

    private void paintTank(Graphics2D g2d, int XOffset, int YOffset){
        AffineTransform backup = g2d.getTransform();
        AffineTransform at = new AffineTransform();
        at.rotate(gunAngle,this.locationX + XOffset + 50, this.locationY + YOffset + 50);
        g2d.transform(at);
        g2d.drawImage(image,locationX + XOffset ,locationY + YOffset ,null);
        g2d.setTransform(backup);
    }

    private void paintGun(Graphics2D g2d, int XOffset, int YOffset){
        AffineTransform backup = g2d.getTransform();
        AffineTransform at = new AffineTransform();
        at.rotate(gunAngle,locationX  + XOffset + 50, locationY + YOffset + image.getHeight()/2);
        g2d.transform(at);
        g2d.drawImage(gunImage,locationX + XOffset + 30,locationY + YOffset + 30,null);
        g2d.setTransform(backup);
    }

    protected void move(){
        int backUpLocationX = locationX;
        int backUpLocationY = locationY;
        angle = Math.atan2(battleField.getPlayerTank().getLocationY() - locationY,battleField.getPlayerTank().getLocationX() - locationX);
        locationX += speed * Math.cos(angle);
        locationY += speed * Math.sin(angle);
        System.out.println(locationX + " " + locationY);
        battleField.collisionTest(this);


    }

    protected void checkNear(){
        isNear = (Math.hypot(battleField.getPlayerTank().getLocationX() - locationX ,battleField.getPlayerTank().getLocationY() - locationY) < 200)
                && (Math.hypot(battleField.getPlayerTank().getLocationX() - locationX ,battleField.getPlayerTank().getLocationY() - locationY) > 100);
    }

    protected void aim(){
        gunAngle = Math.atan2(battleField.getPlayerTank().getLocationY() - (locationY + 50),battleField.getPlayerTank().getLocationX() - (locationX + 50));
    }

    protected abstract void shot();

    @Override

    public void act() {

    }

}
