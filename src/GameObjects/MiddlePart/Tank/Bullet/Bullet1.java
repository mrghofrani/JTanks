package GameObjects.MiddlePart.Tank.Bullet;


import GameBasis.BattleField;
import GameObjects.GameObject;
import GameObjects.MiddlePart.Exploder;
import GameObjects.MiddlePart.MiddlePart;

public class Bullet1 extends Bullet {

    public Bullet1(BattleField battleField, int locationX, int locationY, int mouseX, int mouseY) {
        this.locationX = locationX;
        this.locationY = locationY;
        this.battleField = battleField;
        angle = Math.atan2(mouseY - locationY, mouseX - locationX);
        this.IMAGE_PATH += "bullet1.png";
        setImage();
        playSound("mashinegun.wav");
        move();
    }


}
