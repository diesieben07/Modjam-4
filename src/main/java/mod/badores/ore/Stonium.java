package mod.badores.ore;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

/**
 * @author diesieben07
 */
public class Stonium extends AbstractOre {

	@Override
	public String getName() {
		return "stonium";
	}

    @Override
    protected int veinsPerChunk(Random r, World w, int chunkX, int chunkZ) {
        return r.nextInt(20);
    }

    @Override
    protected int veinSize() {
        return 10;
    }

    @Override
    public int harvestLevelRequired() {
        return 0;
    }

    @Override
    public float getHardness(World world, int x, int y, int z) {
        return 1.0f;
    }

    @Override
	public void getDroppedItems(World world, int x, int y, int z, int meta, int fortune, List<ItemStack> drops) {
		drops.add(new ItemStack(Blocks.stone));
	}
}
