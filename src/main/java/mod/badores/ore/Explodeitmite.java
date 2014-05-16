package mod.badores.ore;

import cpw.mods.fml.relauncher.Side;
import mod.badores.oremanagement.BlockInfo;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import java.util.Random;

/**
 * @author diesieben07
 */
public class Explodeitmite extends AbstractOre {

	@Override
	protected int veinsPerChunk(Random r, World w, int chunkX, int chunkZ) {
		return r.nextInt(10) == 0 ? 1 : 0;
	}

	@Override
	public int initialTickRate() {
		return 10000;
	}

	@Override
	public void tick(World world, int x, int y, int z, BlockInfo blockInfo, Random random, Side side) {
		super.tick(world, x, y, z, blockInfo, random, side);

		if (side.isServer()) {
			if (rand.nextInt(4) == 0)
				world.createExplosion(null, x + 0.5, y + 1.5, z + 0.5, 2.0f + rand.nextFloat() * 3.0f, true);
		}
	}

	@Override
	public void onRemove(EntityPlayer miner, World world, int x, int y, int z, Side side) {
        if (side.isServer()) {
            if (rand.nextInt(4) == 0)
                world.createExplosion(null, x + 0.5, y + 1.5, z + 0.5, 2.0f + rand.nextFloat() * 3.0f, true);
        }
	}

	@Override
	public float getHardness(World world, int x, int y, int z) {
		return 8.0f;
	}

	@Override
	public float getExplosionResistance(World world, int x, int y, int z) {
		return 10.0f;
	}

	@Override
	public String getName() {
		return "explodeitmite";
	}
}
