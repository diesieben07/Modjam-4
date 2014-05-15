package mod.badores.ore;

import net.minecraft.util.AxisAlignedBB;

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
