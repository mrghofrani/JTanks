package GameObjects.MiddlePart.Tank.Bullet;

import GameBasis.BattleField;

public class EnemyBullet extends EBullet {
    /**
     * initializing enemy bullet
     * @param battleField is main field reference
     * @param locationX is x coordinate
     * @param locationY is y coordinate
     * @param mouseX is mouse x location
     * @param mouseY is mouse y location
     */
    public EnemyBullet(BattleField battleField, int locationX, int locationY, int mouseX, int mouseY) {
        super();
        this.locationX = locationX;
        this.locationY = locationY;
        this.battleField = battleField;
        speed = 8;
        damage = 2;
        angle = Math.atan2(mouseY - locationY, mouseX - locationX);
        this.IMAGE_PATH += "bullet1.png";
        setImage();
        playSound("emachinegun.wav");
        move();
    }
}
