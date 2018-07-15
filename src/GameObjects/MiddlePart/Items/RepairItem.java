package GameObjects.MiddlePart.Items;

import GameBasis.BattleField;
import GameObjects.GameObject;
import GameObjects.MiddlePart.MiddlePart;

public class RepairItem extends GameObject implements Item,MiddlePart {

    private int gift = 10;
    private BattleField battleField;

    public RepairItem(BattleField battleField,int locationX, int locationY){
        this.locationX = locationX;
        this.locationY = locationY;
        this.IMAGE_PATH += "RepairItem.png";
        setImage();
    }
    /**
     * manage's act of this object
     */
    @Override
    public void act() {
        // Do nothing just be in your place
    }
    /**
     * manage's deleting this object
     */
    @Override
    public void dispose() {
        isDeleted = true;
    }
    /**
     * @return number of adding cartridge
     */
    @Override
    public int getGift() {
        return gift;
    }
}
