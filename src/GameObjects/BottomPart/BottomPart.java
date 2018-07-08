package GameObjects.BottomPart;

import GameObjects.GameObject;

public interface BottomPart {
    class ExplodedGround extends GameObject {
        public ExplodedGround(int locationX,int locationY){
            this.locationX = locationX;
            this.locationY = locationY;
            this.IMAGE_PATH += "ExplodedGround.png";
            setImage();
        }

        @Override
        public void act() {
            // Do nothing just be in your place
        }
    }
}
