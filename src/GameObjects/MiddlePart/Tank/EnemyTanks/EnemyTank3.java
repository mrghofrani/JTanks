package GameObjects.MiddlePart.Tank.EnemyTanks;

import GameBasis.BattleField;
import GameObjects.MiddlePart.HardObject;
import GameObjects.MiddlePart.Tank.Bullet.EnemyBullet;
import GameObjects.MiddlePart.Tank.Bullet.EnemyCannon;
import ThreadPool.ThreadPool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EnemyTank3 extends EnemyTankTemplate implements HardObject {

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
        fireThread = new Thread() {
            @Override
            public void run() {
                Timer fireTimer = new Timer(1000, new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(checkNearGun()){
                            if(health > 0) {
                                shot();
                            }
                        }
                    }
                });
                fireTimer.start();
            }
        };

        fireThread.start();
        aimThread = new Thread() {
            @Override
            public void run() {
                Timer aimTimer = new Timer(100, new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(health > 0) {
                            aim();
                        }
                    }
                });
                aimTimer.start();
            }
        };
        aimThread.start();
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
    public void stop() {
        // like a hard wall
    }
}
