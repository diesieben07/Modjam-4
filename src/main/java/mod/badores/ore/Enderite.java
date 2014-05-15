package mod.badores.ore;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;

/**
 * @author diesieben07
 */
public class Enderite extends AbstractOre {

	@Override
	public void onHarvest(EntityPlayer miner, World world, int x, int y, int z, Side side) {
		if (side.isServer()) {
			ChunkCoordinates coords = findRandomSpot(world, x, y, z);
			miner.setPositionAndUpdate(coords.posX, coords.posY, coords.posZ);
		}
	}

	private static final int RADIUS = 40;
	private static final int RAD_DOUBLE = RADIUS * 2;

	private ChunkCoordinates findRandomSpot(World world, int x, int y, int z) {
		int rX = x - RADIUS + rand.nextInt(RAD_DOUBLE);
		int rZ = z - RADIUS + rand.nextInt(RAD_DOUBLE);
		int rY = 10 + rand.nextInt(240);
		return new ChunkCoordinates(rX, rY, rZ);
	}

	@Override
	public String getName() {
		return "enderite";
	}
}
