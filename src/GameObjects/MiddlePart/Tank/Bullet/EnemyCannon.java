package GameObjects.MiddlePart.Tank.Bullet;

import GameBasis.BattleField;

public class EnemyCannon extends EBullet{
    public EnemyCannon(BattleField battleField, int locationX, int locationY, int mouseX, int mouseY) {
        this.locationX = locationX;
        this.locationY = locationY;
        this.battleField = battleField;
        speed = 3;
        angle = Math.atan2(mouseY - locationY, mouseX - locationX);
        this.IMAGE_PATH += "bullet1.png";
        setImage();
        playSound("emashinegun.wav");
        move();
        System.out.println("shot");
    }
}
