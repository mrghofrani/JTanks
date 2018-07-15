package GameObjects.MiddlePart.Tank.Bullet;
/**
 * This interface represents a Bullet
 */
public interface Bullet {
    /**
     * used to disappear the object after collision
     */
    public void dispose();

    /**
     * @returns the damage value of the bullet
     */
    public int getDamage();
}
