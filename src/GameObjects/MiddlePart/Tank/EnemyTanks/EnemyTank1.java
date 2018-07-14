package GameObjects.MiddlePart.Tank.EnemyTanks;

import GameBasis.BattleField;
import GameObjects.MiddlePart.Tank.Bullet.EnemyCannon;
import ThreadPool.ThreadPool;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EnemyTank1 extends EnemyTankTemplate {

    public EnemyTank1(BattleField battleField, int locationX, int locationY) {
        this.battleField = battleField;
        this.locationX = locationX;
        this.locationY = locationY;
        this.IMAGE_PATH += "enemyTank1.png";
        this.GUN_IMAGE += "enemyGun1.png";
        this.speed = 5;
        this.health = 20;
        isNear = false;
        setImage();
        setGunImage();
        moveThread = new Runnable() {
            @Override
            public void run() {
                Timer timer = new Timer(100, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(act) {
                            checkNear();
                            if (isNear && health > 0) {
                                move();
                            }
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
                Timer fireTimer = new Timer(100, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(act) {
                            if (checkNearGun()) {
                                if (health > 0)
                                    shot();
                            }
                        }
                    }
                });
                fireTimer.start();
            }
        };
        ThreadPool.execute(fireThread);
        aimThread = new Runnable() {
            @Override
            public void run() {
                Timer aimTimer = new Timer(100, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(act){
                            aim();
                        }
                    }
                });
                aimTimer.start();
            }
        };
        ThreadPool.execute(aimThread);
    }


    @Override
    protected void shot() {
        battleField.add(new EnemyCannon(battleField, locationX + 60, locationY + 50, battleField.getPlayerTank().getLocationX() + 50, battleField.getPlayerTank().getLocationY() + 50));
    }

}
