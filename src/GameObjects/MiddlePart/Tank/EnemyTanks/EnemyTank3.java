package GameObjects.MiddlePart.Tank.EnemyTanks;

import GameBasis.BattleField;
import GameObjects.MiddlePart.Tank.Bullet.EnemyBullet;
import GameObjects.MiddlePart.Tank.Bullet.EnemyCannon;
import ThreadPool.ThreadPool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EnemyTank3 extends EnemyTankTemplate {

    public EnemyTank3(BattleField battleField, int locationX, int locationY){
        this.battleField = battleField;
        this.locationX = locationX;
        this.locationY = locationY;
        this.IMAGE_PATH += "enemyTank3.png";
        this.GUN_IMAGE += "enemyGun3.png";
        this.speed = 5;
        this.health = 4;
        isNear = false;
        setImage();
        setGunImage();
        Runnable fireThread = new Runnable() {
            @Override
            public void run() {
                Timer fireTimer = new Timer(1000, new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(checkNearGun()){
                            shot();
                        }
                    }
                });
                fireTimer.start();
            }
        };

        ThreadPool.execute(fireThread);
        Runnable aimThread = new Runnable() {
            @Override
            public void run() {
                Timer aimTimer = new Timer(100, new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        aim();
                    }
                });
                aimTimer.start();
            }
        };
        ThreadPool.execute(aimThread);
    }

    @Override
    protected void shot() {
        battleField.add(new EnemyCannon(battleField,locationX + 60,locationY + 50,battleField.getPlayerTank().getLocationX() + 50,battleField.getPlayerTank().getLocationY() + 50));
    }

    @Override
    protected void paintTank(Graphics2D g2d, int XOffset, int YOffset) {
        g2d.drawImage(image,locationX + XOffset ,locationY + YOffset ,null);
    }

    @Override
    public void damage(double value) {

    }
}
