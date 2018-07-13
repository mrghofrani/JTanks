package GameObjects.MiddlePart.Tank.EnemyTanks;

import GameBasis.BattleField;
import GameObjects.MiddlePart.Tank.Bullet.EnemyBullet;
import GameObjects.MiddlePart.Tank.Bullet.EnemyCannon;

public class EnemyTank1 extends EnemyTankTemplate {

    public EnemyTank1(BattleField battleField, int locationX, int locationY){
        this.battleField = battleField;
        this.locationX = locationX;
        this.locationY = locationY;
        this.IMAGE_PATH += "enemyTank1.png";
        this.GUN_IMAGE += "enemyGun1.png";
        this.speed = 5;
        isNear = false;
        setImage();
        setGunImage();
        act();
    }


    @Override
    protected void shot() {
        battleField.add(new EnemyCannon(battleField,locationX,locationY,battleField.getPlayerTank().getLocationX(),battleField.getPlayerTank().getLocationY()));
    }
}
