package GameObjects.MiddlePart.Tank.EnemyTanks;

import GameBasis.BattleField;
import GameObjects.MiddlePart.HardObject;
import GameObjects.MiddlePart.Tank.Bullet.EnemyBullet;
import ThreadPool.ThreadPool;

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
        moveThread = new Runnable() {
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
        ThreadPool.execute(moveThread);
        fireThread = new Runnable() {
            @Override
            public void run() {
                Timer fireTimer = new Timer(3000, new ActionListener(){
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
        Runnable runnable3 = new Runnable() {
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
        for (int i = 0; i < 2; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            battleField.add(new EnemyBullet(battleField,locationX + 50,locationY + 40,battleField.getPlayerTank().getLocationX() + 50,battleField.getPlayerTank().getLocationY() + 50));
        }
    }

    /**
     * This method runs when
     * a explosive object is
     * going to be damaged
     *
     * @param value
     */
    @Override
    public void explode(int value) {

    }

    @Override
    public void stop() {
        locationX = savedLocationX;
        locationY = savedLocationY;
    }
}
