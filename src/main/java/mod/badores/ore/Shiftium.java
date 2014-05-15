package mod.badores.ore;

import net.minecraft.util.AxisAlignedBB;

/**
 * @author diesieben07
 */
public class Shiftium extends AbstractOre {

    @Override
    public AxisAlignedBB selectionBB() {
        return AxisAlignedBB.getAABBPool().getAABB(0, 0, 0, 0, 0, 0);
    }

    @Override
	public String getName() {
		return "shiftium";
	}
}
