package GameObjects.MiddlePart.Tank.Bullet;

import GameBasis.BattleField;
import GameObjects.GameObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class EnemyTank1 extends GameObject {
    private double angle;
    private int speed = 2;

    private double gunAngle;
    private String GUN_IMAGE = "files" + File.separator + "Images" + File.separator;
    private BufferedImage gunImage;

    private boolean isNear;
    private BattleField battleField;


    public EnemyTank1(BattleField battleField, int locationX, int locationY){
        this.battleField = battleField;
        this.locationX = locationX;
        this.locationY = locationY;
        this.IMAGE_PATH += "enemyTank1.png";
        this.GUN_IMAGE += "enemyGun1.png";
        isNear = false;
        setImage();
        setGunImage();
        act();
    }

    private void setGunImage(){
        File file = new File(GUN_IMAGE);
        try {
            gunImage = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void doRendering(Graphics2D g2d, int XOffset, int YOffset) {
        checkNear();
        if(isNear){
            move();
            aim();
            shot();
        }
        paintTank(g2d,XOffset,YOffset);
        paintGun(g2d,XOffset,YOffset);
    }

    @Override
    public void act() {
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

    private void move(){
        int backUpLocationX = locationX;
        int backUpLocationY = locationY;
        angle = Math.atan2(battleField.getPlayerTank().getLocationY() - locationY,battleField.getPlayerTank().getLocationX() - locationX);
        locationX += speed * Math.cos(angle);
        locationY += speed * Math.sin(angle);
        if(battleField.collisionTest(this)){
            locationX = backUpLocationX;
            locationY = backUpLocationY;
        }
    }


    private void checkNear(){
        isNear = (Math.hypot(battleField.getPlayerTank().getLocationX() - (locationX + 50),battleField.getPlayerTank().getLocationY() - (locationY + 50)) < 1000);
    }

    private void aim(){
        gunAngle = Math.atan2(battleField.getPlayerTank().getLocationY() - (locationY + 50),battleField.getPlayerTank().getLocationX() - (locationX + 50));
    }

    private void shot(){
//        battleField.add(new )
    }
}
