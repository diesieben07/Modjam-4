package mod.badores.ore;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

/**
 * @author diesieben07
 */
public class Paintitwhite extends AbstractOre {

	@Override
	public String getName() {
		return "paintitwhite";
	}

    @Override
    protected Block replace() {
        return Blocks.gravel;
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
        return 32;
    }

    @Override
    protected int genMax(Random random, World world, int chunkX, int chunkZ) {
        return 128;
    }

    @Override
    protected int veinSize() {
        return 5;
    }

    @Override
    protected int veinsPerChunk(Random r, World w, int chunkX, int chunkZ) {
        return 2 + r.nextInt(5);
    }

    @Override
	public void addDroppedItems(World world, int x, int y, int z, int meta, int fortune, List<ItemStack> drops, boolean isIngotBlock) {
		drops.add(new ItemStack(Items.dye, 1, 15));
	}
}
