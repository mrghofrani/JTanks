package GameObjects.MiddlePart;

/**
 * This Class is is useful to
 * find whether an object is
 * damagable or not
 */
public interface Explosive {
    /**
     * This method runs when
     * a explosive object is
     * going to be damaged
     */
    public void damage(double value);
}
