package GameObjects.MiddlePart.Items;

import GameObjects.GameObject;
import GameObjects.MiddlePart.MiddlePart;

public class RepairItem extends GameObject implements Item,MiddlePart {

    public RepairItem(int locationX,int locationY){
        this.locationX = locationX;
        this.locationY = locationY;
        this.IMAGE_PATH += "RepairItem.png";
        setImage();
    }

    @Override
    public void act() {
        // Do nothing just be in your place
    }
}
