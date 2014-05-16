package mod.badores.ore;

import net.minecraft.world.World;

/**
 * @author diesieben07
 */
public class Unobtainium extends AbstractOre {

	@Override
	public float getHardness(World world, int x, int y, int z) {
		return -18000000.0F;
	}

	@Override
	public String getName() {
		return "unobtainium";
	}
}
