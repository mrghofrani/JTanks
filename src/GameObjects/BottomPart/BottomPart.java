package GameObjects.BottomPart;

import GameObjects.GameObject;
/**
 * A class for defining objects that are on the ground
 */
public interface BottomPart {
    class ExplodedGround extends GameObject {
        public ExplodedGround(int locationX,int locationY){
            this.locationX = locationX;
            this.locationY = locationY;
            this.IMAGE_PATH += "ExplodedGround.png";
            setImage();
        }
        /**
         * defines act of class
         */
        @Override
        public void act() {
            // Do nothing just be in your place
        }
    }
}
