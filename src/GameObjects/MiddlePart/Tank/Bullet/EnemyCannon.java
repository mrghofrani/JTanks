package GameObjects.MiddlePart.Tank.Bullet;

import GameBasis.BattleField;

public class EnemyCannon extends EBullet{
    /**
     * first initializing of fields
     * @param battleField
     * @param locationX
     * @param locationY
     * @param mouseX
     * @param mouseY
     */
    public EnemyCannon(BattleField battleField, int locationX, int locationY, int mouseX, int mouseY) {
        super();
        this.locationX = locationX;
        this.locationY = locationY;
        this.battleField = battleField;
        speed = 3;
        damage = 8;
        angle = Math.atan2(mouseY - locationY, mouseX - locationX);
        this.IMAGE_PATH += "MyCannon1.png";
        setImage();
        playSound("eshot.wav");
        move();
    }
}
