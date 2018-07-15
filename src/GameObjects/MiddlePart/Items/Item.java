package GameObjects.MiddlePart.Items;
/**
 * This class represents items of middle part
 */
public interface Item {
    /**
     * This method is used to disappear the eaten item
     */
    public void dispose();
    /**
     * @returns the gift of the item
     */
    public int getGift();
}
