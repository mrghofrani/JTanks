package GameObjects.MiddlePart.Tank.EnemyTanks;

import GameBasis.BattleField;
import GameObjects.MiddlePart.Tank.Bullet.EnemyBullet;
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
                            move();
                            aim();
                        }
                    }
                });
                timer.start();
                Timer fireTimer = new Timer(1000, new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        checkNear();
                        if(Math.hypot(battleField.getPlayerTank().getLocationX() - locationX ,battleField.getPlayerTank().getLocationY() - locationY) < 200
                                && health > 0){
                            shot();
                            System.out.println("here");
                        }
                    }
                });
                fireTimer.start();
            }
        };
        ThreadPool.execute(runnable);

    }


    @Override
    protected void shot() {
        battleField.add(new EnemyCannon(battleField,locationX + 60,locationY + 50,battleField.getPlayerTank().getLocationX(),battleField.getPlayerTank().getLocationY()));
    }
}
