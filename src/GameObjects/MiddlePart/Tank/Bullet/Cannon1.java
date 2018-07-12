package GameObjects.MiddlePart.Tank.Bullet;


public class Cannon1 extends Bullet{


    public Cannon1(int locationX, int locationY, int mouseX, int mouseY){
        this.locationX = locationX;
        this.locationY = locationY;
        angle = Math.atan2(mouseY - locationY,mouseX - locationX);
        this.IMAGE_PATH += "Cannon1.png";
        setImage();
        playSound("cannon.wav");
        act();
    }


    @Override
    public void act() {
    }

    public void move(){
        locationX += speed * Math.cos(angle);
        locationY += speed * Math.sin(angle);
    }


}
