package mod.badores.ore;

/**
 * @author diesieben07
 */
public class Crappium extends AbstractOre {

	@Override
	public boolean hasTools() {
		return true;
	}

	@Override
	public boolean hasIngot() {
		return true;
	}

	@Override
	public ToolInfo getToolInfo() {
		return new ToolInfo(0, 1, 2.0F, 0.0F, 15);
	}

	@Override
	public String getName() {
		return "crappium";
	}
}
