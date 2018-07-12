package GameObjects.MiddlePart.Items;

import GameObjects.GameObject;

public class UpgradeGunItem extends GameObject implements Item {

    public UpgradeGunItem(int locationX,int locationY){
        this.locationX = locationX;
        this.locationY = locationY;
        this.IMAGE_PATH += "upgradeGunItem.png";
        setImage();
    }


    @Override
    public void act() {

    }
}
