package GameObjects.MiddlePart.Tank.Bullet;


import GameObjects.GameObject;
import GameObjects.MiddlePart.Exploder;
import GameObjects.MiddlePart.MiddlePart;

public class Bullet1 extends Bullet {

    public Bullet1(int locationX, int locationY, int mouseX, int mouseY) {
        this.locationX = locationX;
        this.locationY = locationY;
        angle = Math.atan2(mouseY - locationY, mouseX - locationX);
        this.IMAGE_PATH += "bullet1.png";
        setImage();
        playSound("mashinegun.wav");
        move();
    }


}
