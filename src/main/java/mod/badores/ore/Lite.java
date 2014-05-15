package mod.badores.ore;

/**
 * @author diesieben07
 */
public class Lite extends AbstractOre {

    @Override
    public int lightLevel() {
        return 15;
    }

    @Override
	public String getName() {
		return "lite";
	}
}
