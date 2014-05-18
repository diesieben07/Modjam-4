package mod.badores.ore;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

import java.util.Random;

/**
 * @author diesieben07
 */
public class Marmite extends AbstractOre {

	@Override
	public boolean hasIngot() {
		return true;
	}

	@Override
	protected Block replace() {
		return Blocks.clay;
	}

    @Override
    public int harvestLevelRequired(boolean isIngotBlock) {
        return 0;
    }

    @Override
    public String toolRequired(boolean isIngotBlock) {
        return "shovel";
    }

    @Override
    protected int genMin(Random random, World world, int chunkX, int chunkZ) {
        return 64;
    }

    @Override
    protected int genMax(Random random, World world, int chunkX, int chunkZ) {
        return 128;
    }

    @Override
	public String getName() {
		return "marmite";
	}
}
