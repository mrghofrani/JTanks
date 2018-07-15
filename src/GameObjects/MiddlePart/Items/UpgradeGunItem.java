package GameObjects.MiddlePart.Items;

import GameBasis.BattleField;
import GameObjects.GameObject;

public class UpgradeGunItem extends GameObject implements Item {

    private BattleField battleField;
    private int gift = 1;

    public UpgradeGunItem(BattleField battleField, int locationX, int locationY){
        this.locationX = locationX;
        this.locationY = locationY;
        this.battleField = battleField;
        this.IMAGE_PATH += "upgradeGunItem.png";
        setImage();
    }

    /**
     * manage's act of this object
     */
    @Override
    public void act() {

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
     * (this object doesn't add cartride but upgrade current gun of tank)
     */
    @Override
    public int getGift() {
        return gift;
    }
}
