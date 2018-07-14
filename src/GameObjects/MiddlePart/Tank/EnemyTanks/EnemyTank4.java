package GameObjects.MiddlePart.Tank.EnemyTanks;

import GameBasis.BattleField;
import GameObjects.MiddlePart.Exploder;
import GameObjects.MiddlePart.Explosive;
import ThreadPool.ThreadPool;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EnemyTank4 extends EnemyTankTemplate implements Exploder {
    private int damage;
    public EnemyTank4 (BattleField battleField, int locationX, int locationY){
        this.battleField = battleField;
        this.locationX = locationX;
        this.locationY = locationY;
        damage = 10;
        this.IMAGE_PATH += "enemyTank4.png";
        this.GUN_IMAGE += "enemyGun4.png";
        isNear = false;
        setImage();
        this.speed = 10;
        this.health = 1;
        setImage();

        Runnable moveThread = new Runnable() {
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

    protected void checkNear(){
        isNear = Math.hypot(battleField.getPlayerTank().getLocationX() - locationX ,battleField.getPlayerTank().getLocationY() - locationY) < 200;
    }
    @Override
    protected void shot() {

    }

    @Override
    public void explode() {

    }

    @Override
    public int getDamage() {
        return damage;
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
        // Shouldn't be implemented
    }
}
