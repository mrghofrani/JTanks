package GameObjects.MiddlePart.Tank.EnemyTanks;

import GameBasis.BattleField;
import GameObjects.GameObject;
import GameObjects.MiddlePart.Explosive;
import GameObjects.MiddlePart.HardObject;
import GameObjects.MiddlePart.MiddlePart;
import ThreadPool.ThreadPool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public abstract class EnemyTankTemplate extends GameObject implements MiddlePart,Explosive {
    protected double angle;
    protected int speed;

    protected double gunAngle;
    protected String GUN_IMAGE = "files" + File.separator + "Images" + File.separator;
    protected BufferedImage gunImage;
    protected int savedLocationX;
    protected int savedLocationY;

    protected boolean isNear;
    protected BattleField battleField;
    protected int turn = 0;

    protected int health;
    protected Thread moveThread;
    protected Thread fireThread;
    protected Thread aimThread;


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

    protected void paintTank(Graphics2D g2d, int XOffset, int YOffset){
        AffineTransform backup = g2d.getTransform();
        AffineTransform at = new AffineTransform();
        at.rotate(gunAngle,this.locationX + XOffset + 50, this.locationY + YOffset + 50);
        g2d.transform(at);
        g2d.drawImage(image,locationX + XOffset ,locationY + YOffset ,null);
        g2d.setTransform(backup);
    }

    protected void paintGun(Graphics2D g2d, int XOffset, int YOffset){
        AffineTransform backup = g2d.getTransform();
        AffineTransform at = new AffineTransform();
        at.rotate(gunAngle,locationX  + XOffset + 50, locationY + YOffset + image.getHeight()/2);
        g2d.transform(at);
        g2d.drawImage(gunImage,locationX + XOffset + 30,locationY + YOffset + 30,null);
        g2d.setTransform(backup);
    }

    protected void move(){
        savedLocationX = locationX;
        savedLocationY = locationY;
        angle = Math.atan2(battleField.getPlayerTank().getLocationY() - locationY,battleField.getPlayerTank().getLocationX() - locationX);
        locationX += speed * Math.cos(angle);
        locationY += speed * Math.sin(angle);
        battleField.collision(this);
    }

    protected void checkNear(){
        isNear = (Math.hypot(battleField.getPlayerTank().getLocationX() - locationX ,battleField.getPlayerTank().getLocationY() - locationY) < 200)
                && (Math.hypot(battleField.getPlayerTank().getLocationX() - locationX ,battleField.getPlayerTank().getLocationY() - locationY) > 100);
    }

    protected boolean checkNearGun(){
        return Math.hypot(battleField.getPlayerTank().getLocationX() - locationX ,battleField.getPlayerTank().getLocationY() - locationY) < 2000 && health >0;
    }

    protected void aim(){
        gunAngle = Math.atan2(battleField.getPlayerTank().getLocationY() - (locationY + 50),battleField.getPlayerTank().getLocationX() - (locationX + 50));
    }

    protected abstract void shot();

    @Override

    public void act() {

    }

    public void stop(){
        locationY = savedLocationY;
        locationX = savedLocationX;
    }

    /**
     * This method runs when
     * a explosive object is
     * going to be damaged
     *
     * @param value
     */
    public void explode(int value) {
        if (health - value > 0)
            health -= value;
        else {
            Runnable stop = new Runnable() {
                @Override
                public void run() {
                    javax.swing.Timer aimTimer = new javax.swing.Timer(1, new ActionListener(){
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            health = 0;
                            isDeleted = true;
                            if(aimThread != null)
                                aimThread.interrupt();
                            if(moveThread != null)
                                moveThread.interrupt();
                            if(fireThread != null)
                                fireThread.interrupt();
                        }
                    });
                    aimTimer.start();
                }
            };
            ThreadPool.execute(stop);
        }
    }

    public double getAngle() {
        return angle;
    }

    @Override
    public int getHealth() {
        return health;
    }

}
