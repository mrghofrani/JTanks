package GameObjects.MiddlePart.Tank.EnemyTanks;

import GameBasis.BattleField;
import GameObjects.MiddlePart.HardObject;
import GameObjects.MiddlePart.Tank.Bullet.EnemyBullet;
import ThreadPool.ThreadPool;
import sun.print.PrintJob2D;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EnemyTank2 extends EnemyTankTemplate implements HardObject {

    public EnemyTank2(BattleField battleField, int locationX, int locationY){
        this.battleField = battleField;
        this.locationX = locationX;
        this.locationY = locationY;
        this.IMAGE_PATH += "enemyTank2.png";
        this.speed = 7;
        this.health = 1;
        isNear = false;
        setImage();
        moveThread = new Thread() {
            @Override
            public void run() {
                Timer timer =  new Timer(100, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        checkNear();
                        if(isNear && health > 0){
                            move();
                        }
                    }
                });
                timer.start();
            }
        };
        moveThread.interrupt();
        fireThread = new Thread() {
            @Override
            public void run() {
                Timer fireTimer = new Timer(3000, new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(health > 0) {
                            if (checkNearGun()) {
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
        for (int i = 0; i < 2; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            battleField.add(new EnemyBullet(battleField,locationX + 50,locationY + 40,battleField.getPlayerTank().getLocationX() + 50,battleField.getPlayerTank().getLocationY() + 50));
        }
    }


    @Override
    public void stop() {
        locationX = savedLocationX;
        locationY = savedLocationY;
    }
}
