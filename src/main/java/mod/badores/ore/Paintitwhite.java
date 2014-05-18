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
	public void addOreDrops(World world, int x, int y, int z, int meta, int fortune, List<ItemStack> drops) {
		drops.add(new ItemStack(Items.dye, 1, 15));
	}
}
