package mod.badores.ore;

/**
 * @author diesieben07
 */
public class Shiftium extends AbstractOre {

	@Override
	public boolean shouldSelectionRayTrace() {
		return false;
	}

	@Override
	public String getName() {
		return "shiftium";
	}
}
