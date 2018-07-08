package GameObjects.MiddlePart.Items;

import GameObjects.GameObject;
import GameObjects.MiddlePart.MiddlePart;

public class MachineGunCartridgeItem extends GameObject implements Item,MiddlePart {

    public MachineGunCartridgeItem(int locationX,int locationY){
        this.locationX = locationX;
        this.locationY = locationY;
        this.IMAGE_PATH += "MachineGunCartridgeItem.png";
        setImage();
    }


    @Override
    public void act() {
        // Do nothing just be in your place
    }
}
