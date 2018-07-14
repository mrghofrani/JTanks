package GameObjects.MiddlePart.Items;

import GameBasis.BattleField;
import GameObjects.GameObject;
import GameObjects.MiddlePart.MiddlePart;

public class MachineGunCartridgeItem extends GameObject implements Item,MiddlePart {

    private int gift = 250;
    BattleField battleField;

    public MachineGunCartridgeItem(BattleField battleField,int locationX, int locationY){
        this.locationX = locationX;
        this.locationY = locationY;
        this.IMAGE_PATH += "MachineGunCartridgeItem.png";
        setImage();
    }

    @Override
    public void act() {
        // Do nothing just be in your place
    }

    @Override
    public void dispose() {
        isDeleted = true;
    }

    @Override
    public int getGift() {
        return gift;
    }
}
