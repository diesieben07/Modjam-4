package mod.badores.ore;

import mod.badores.oremanagement.ArmorInfo;
import mod.badores.oremanagement.ToolInfo;

/**
 * @author diesieben07
 */
public class Copper extends AbstractOre {

	@Override
	public boolean hasTools() {
		return true;
	}

	@Override
	public ToolInfo getToolInfo() {
		return new ToolInfo(0, 1, 2.0F, 0.0F, 15);
	}

	@Override
	public boolean hasArmor() {
		return true;
	}

	@Override
	public ArmorInfo getArmorInfo() {
		return new ArmorInfo(100, new int[]{1, 3, 2, 1}, 15);
	}

	@Override
	public String getName() {
		return "copper";
	}
}
