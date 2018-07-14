package GameObjects.MiddlePart.Tank.EnemyTanks;

import GameBasis.BattleField;
import GameObjects.MiddlePart.HardObject;
import GameObjects.MiddlePart.Tank.Bullet.EnemyCannon;
import ThreadPool.ThreadPool;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EnemyTank1 extends EnemyTankTemplate implements HardObject {

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
        moveThread = new Thread() {
            @Override
            public void run() {
                Timer timer = new Timer(1000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        checkNear();
                        if (isNear && health > 0 && !isDeleted) {
                            move();
                        }
                    }
                });
                timer.start();
            }
        };
        moveThread.start();
        fireThread = new Thread() {
            @Override
            public void run() {
                Timer fireTimer = new Timer(1000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (!isDeleted) {
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
                Timer aimTimer = new Timer(100, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (!isDeleted)
                            aim();
                    }
                });
                aimTimer.start();
            }
        };
        aimThread.start();
    }


    @Override
    protected void shot() {
        battleField.add(new EnemyCannon(battleField, locationX + 60, locationY + 50, battleField.getPlayerTank().getLocationX() + 50, battleField.getPlayerTank().getLocationY() + 50));
    }

}
