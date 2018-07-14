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


    @Override
    public void act() {

    }

    @Override
    public void dispose() {
        gift = 0;
    }

    @Override
    public int getGift() {
        return gift;
    }
}
