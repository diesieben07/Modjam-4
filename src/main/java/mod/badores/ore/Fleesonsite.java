package mod.badores.ore;

import cpw.mods.fml.relauncher.Side;
import mod.badores.entities.EntityFleeingBlock;
import mod.badores.oremanagement.BlockInfo;
import net.minecraft.world.World;

import java.util.Random;

/**
 * @author diesieben07
 */
public class Fleesonsite extends AbstractOre {

	@Override
	public int initialTickRate() {
		return 40;
	}

	@Override
	public void tick(World world, int x, int y, int z, BlockInfo blockInfo, Random random, Side side) {
		super.tick(world, x, y, z, blockInfo, random, side);

		if (world.getClosestPlayer(x + 0.5, y + 0.5, z + 0.5, 6.0) != null) {
			world.setBlockToAir(x, y, z);
			EntityFleeingBlock fleeingBlock = new EntityFleeingBlock(world, blockInfo.block, blockInfo.metadata);
			fleeingBlock.setPosition(x + 0.5, y, z + 0.5);
            world.spawnEntityInWorld(fleeingBlock);
		}
	}

	@Override
	public String getName() {
		return "fleesonsite";
	}
}
