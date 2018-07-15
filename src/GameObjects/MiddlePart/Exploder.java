package GameObjects.MiddlePart;
/**
 * This is label used to determine sth is
 * of this type or not
 */
public interface Exploder {
    /**
     * handle's explode of the bullets and exploder things
     */
    public void explode();
    /**
     * @returns damage value of the exploder object
     */
    public int getDamage();
}
