package mod.badores.ore;

/**
 * @author diesieben07
 */
public class Crappium extends AbstractOre {

	@Override
	public boolean canMakeTools() {
		return true;
	}

	@Override
	public ToolInfo getToolInfo() {
		return new ToolInfo(0, 59, 2.0F, 0.0F, 15);
	}

	@Override
	public String getName() {
		return "crappium";
	}
}
