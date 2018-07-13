package GameObjects.MiddlePart.Tank.EnemyTanks;

import GameBasis.BattleField;
import GameObjects.MiddlePart.Tank.Bullet.EBullet;

public class EnemyTank4 extends EnemyTankTemplate {

    public EnemyTank4 (BattleField battleField, int locationX, int locationY){
        this.battleField = battleField;
        this.locationX = locationX;
        this.locationY = locationY;
        this.IMAGE_PATH += "enemyTank4.png";
        this.GUN_IMAGE += "enemyGun4.png";
        isNear = false;
        setImage();
        setGunImage();
//        act();
    }

    @Override
    protected void shot() {

    }
}
