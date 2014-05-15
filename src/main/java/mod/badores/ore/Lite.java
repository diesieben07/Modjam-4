package mod.badores.ore;

/**
 * @author diesieben07
 */
public class Lite extends AbstractOre {

    @Override
    public float lightLevel() {
        return 1.0f;
    }

    @Override
	public String getName() {
		return "lite";
	}
}
