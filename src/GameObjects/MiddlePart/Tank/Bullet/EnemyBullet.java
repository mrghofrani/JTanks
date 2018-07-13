package GameObjects.MiddlePart.Tank.Bullet;

import GameBasis.BattleField;

public class EnemyBullet extends EBullet {

    public EnemyBullet(BattleField battleField, int locationX, int locationY, int mouseX, int mouseY) {
        this.locationX = locationX;
        this.locationY = locationY;
        this.battleField = battleField;
        speed = 8;
        angle = Math.atan2(mouseY - locationY, mouseX - locationX);
        this.IMAGE_PATH += "bullet1.png";
        setImage();
        playSound("eshot.wav");
    }
}
