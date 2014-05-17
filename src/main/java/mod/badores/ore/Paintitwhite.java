package mod.badores.ore;

import mod.badores.oremanagement.BlockInfo;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.Arrays;
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
    public int harvestLevelRequired() {
        return 0;
    }

    @Override
    public String toolRequired() {
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
	public List<ItemStack> getDroppedItems(World world, int x, int y, int z, int meta, int fortune) {
		return Arrays.asList(new ItemStack(Items.dye, 1, 15));
	}
}
