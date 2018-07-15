package GameObjects.MiddlePart.Tank.EnemyTanks;

import GameBasis.BattleField;
import GameObjects.MiddlePart.HardObject;
import GameObjects.MiddlePart.Tank.Bullet.EnemyCannon;
import ThreadPool.ThreadPool;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EnemyTank1 extends EnemyTankTemplate implements HardObject {
    /**
     * represent's one of enemy tanks
     * @param battleField
     * @param locationX
     * @param locationY
     */
    public EnemyTank1(BattleField battleField, int locationX, int locationY) {
        this.battleField = battleField;
        this.locationX = locationX;
        this.locationY = locationY;
        this.IMAGE_PATH += "enemyTank1.png";
        this.GUN_IMAGE += "enemyGun1.png";
        this.speed = 5;
        this.health = 30;
        isNear = false;
        setImage();
        setGunImage();

        /**
         * this thread handle's moving of enemy tank
         */
        moveThread = new Thread() {
            @Override
            public void run() {
                Timer timer = new Timer(1000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        checkNear();
                        if (isNear && health > 0 ) {
                            move();
                        }
                    }
                });
                timer.start();
            }
        };
        moveThread.start();
        /**
         * this thread represent's firing of enemy tank
         */
        fireThread = new Thread() {
            @Override
            public void run() {
                Timer fireTimer = new Timer(1000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (health > 0) {
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
        /**
         * represent's aiming of tank
         */
        aimThread = new Thread() {
            @Override
            public void run() {
                Timer aimTimer = new Timer(100, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (health > 0)
                            aim();
                    }
                });
                aimTimer.start();
            }
        };
        aimThread.start();
    }

    /**
     * handle's shot of tank
     */
    @Override
    protected void shot() {
        battleField.add(new EnemyCannon(battleField, locationX + 60, locationY + 50, battleField.getPlayerTank().getLocationX() + 50, battleField.getPlayerTank().getLocationY() + 50));
    }

}
