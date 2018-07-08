package GameObjects.BottomPart;

import GameObjects.GameObject;

public class Ground extends GameObject implements  BottomPart {

    public Ground(int locationX,int locationY){
        this.locationX = locationX;
        this.locationY = locationY;
        this.IMAGE_PATH += "Ground.png";
        setImage();
    }

    @Override
    public void act() {
        // Do nothing just be in your place
    }
}
