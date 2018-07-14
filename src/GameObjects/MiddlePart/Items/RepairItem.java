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

    @Override
    public void act() {
        // Do nothing just be in your place
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
