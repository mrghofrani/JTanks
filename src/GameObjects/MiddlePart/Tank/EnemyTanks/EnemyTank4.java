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
    /**
     * represent's a kind of enemy tanks(khengEnemy)
     * @param battleField
     * @param locationX
     * @param locationY
     */
    public EnemyTank4 (BattleField battleField, int locationX, int locationY){
        this.battleField = battleField;
        this.locationX = locationX;
        this.locationY = locationY;
        savedLocationX = locationX;
        savedLocationY = locationY;
        damage = 10;
        this.IMAGE_PATH += "enemyTank4.png";
        this.GUN_IMAGE += "enemyGun4.png";
        isNear = false;
        setImage();
        this.speed = 10;
        this.health = 10;
        this.damage = 10;
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
        moveThread.start();

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
    /**
     * this method job is checking if player tank is neat it zone or no
     */
    protected void checkNear(){
        isNear = Math.hypot(battleField.getPlayerTank().getLocationX() - locationX ,battleField.getPlayerTank().getLocationY() - locationY) < 200;
    }
    /**
     * shotting action is not mentioned for this animal
     */
    @Override
    protected void shot() {

    }
    /**
     * trigger's  exploding action is this method job
     */
    @Override
    public void explode() {
        dispose();
    }
    /**
     * handle deleting this enemy
     */
    public void dispose() {
        playSound("heavygun.wav");
        isDeleted = true;
    }
    /**
     * @return damage value
     */
    @Override
    public int getDamage() {
        return damage;
    }


}
