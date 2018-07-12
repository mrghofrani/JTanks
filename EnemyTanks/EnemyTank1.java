package GameObjects.MiddlePart.Tank.EnemyTanks;

import GameBasis.BattleField;
import GameObjects.MiddlePart.Tank.UserTank.CannonGun;
import com.sun.xml.internal.bind.v2.model.annotation.RuntimeAnnotationReader;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class EnemyTank1 extends EnemyTank {

    public EnemyTank1(int LocationX, int LocationY){
        this.health = 1;
        this.speed = 3;
        this.locationX = LocationX;
        this.locationY = LocationY;
        this.IMAGE_PATH += "EnemyTank1Pack.png";
        this.GUN_IMAGE_PATH += "EnemyTank1Gun.png";
        setImage();
        setGunImage();
        TimerTask task = new TimerTask() {
            public void run() {
                int playerLocationX = BattleField.getPlayerLocationX(LocationX,LocationY);
                int playerLocationY = BattleField.getPlayerLocationY(LocationX,LocationY);
                double angle = Math.atan2(playerLocationX - locationX - image.getWidth()/2,playerLocationY - locationY - image.getHeight()/2);
                if(Math.hypot(playerLocationX - locationX, playerLocationY - locationY) < 25){
                    BattleField.add(new CannonGun(locationX,locationY,angle));
                }
                else{
                    Math.
                }

            }

        };
    }

    public void paintGun(Graphics2D g2d){

    }

    public void paintTank(Graphics2D g2d){

    }

    @Override
    public void fire() {

    }

    @Override
    public void rotateTank(boolean direction) {

    }

    @Override
    public void act(){

    }

    private class Act implements Runnable{

        @Override
        public void run() {
            int playerLocationX = BattleField.getPlayerLocationX(locationX,locationY);
            int playerLocationY = BattleField.getPlayerLocationY(locationX,locationY);
            double angle = Math.atan2(playerLocationX - locationX - image.getWidth()/2,playerLocationY - locationY - image.getHeight()/2);

            // TODO : I have no Idea about drawing tank's Gun

            Timer timer = new Timer(, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int gunEndPositionX = gunPosX + tankGun.getWidth()/2;
                    int gunEndPositionY = gunPosY + tankGun.getHeight()/2;
                    double angle = Math.atan2((targets[0].getLocationY() - gunEndPositionY)  ,(targets[1].getLocationX() - gunEndPositionX)) * (180/Math.PI);
                    MyCannonBullet2 bullet = new MyCannonBullet2(gunEndPositionX,gunEndPositionY,(int)angle);
                    missiles.add(bullet);
                    bullet.move();
                }
            });
            timer.start();
        }
    }

}
