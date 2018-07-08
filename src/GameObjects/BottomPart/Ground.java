package GameObjects.BottomPart;

import GameObjects.GameObject;

public class Ground extends GameObject implements  BottomPart {

    private boolean startingPoint = false;
    private boolean finishingPoint = false;

    public Ground(int locationX,int locationY){
        this.locationX = locationX;
        this.locationY = locationY;
        this.IMAGE_PATH += "Ground.png";
        setImage();
    }

    /**
     * this method is used to set the
     * Ground as starting point
     */
    public void setStartingPoint(){
        startingPoint = true;
    }

    /**
     * this method is used to set the
     * Ground as finishing point
     */
    public void setFinishingPoint(){
        finishingPoint = true;
    }

    /**
     * {@return true} if the ground was finishing point otherwise false
     */
    public boolean isFinishingPoint(){
        return finishingPoint;
    }

    /**
     * {@return true} if the ground was starting point otherwise false
     * */
    public boolean isStartingPoint(){
        return startingPoint;
    }


    @Override
    public void act() {
        // Do nothing just be in your place
    }
}
