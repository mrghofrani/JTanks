package GameObjects.MiddlePart.Tank.EnemyTanks;

import GameBasis.BattleField;
import GameObjects.MiddlePart.Tank.Bullet.EnemyCannon;
import ThreadPool.ThreadPool;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EnemyTank1 extends EnemyTankTemplate {

    public EnemyTank1(BattleField battleField, int locationX, int locationY){
        this.battleField = battleField;
        this.locationX = locationX;
        this.locationY = locationY;
        this.IMAGE_PATH += "enemyTank1.png";
        this.GUN_IMAGE += "enemyGun1.png";
        this.speed = 5;
        this.health = 2;
        isNear = false;
        setImage();
        setGunImage();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Timer timer =  new Timer(100, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        checkNear();
                        if(isNear && health > 0){
                            savedLocationX = locationX;
                            savedLocationY = locationY;
                            move();
                            battleField.collision(EnemyTank1.this);
                        }
                    }
                });
                timer.start();
            }
        };
        ThreadPool.execute(runnable);
        Runnable runnable2 = new Runnable() {
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
        ThreadPool.execute(runnable2);
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
        ThreadPool.execute(runnable3);
    }


    @Override
    protected void shot() {
        battleField.add(new EnemyCannon(battleField,locationX + 60,locationY + 50,battleField.getPlayerTank().getLocationX() + 50,battleField.getPlayerTank().getLocationY() + 50)); }


    @Override
    public void stop() {
        locationX = savedLocationX;
        locationY = savedLocationY;
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
}
