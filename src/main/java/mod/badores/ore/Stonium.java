package mod.badores.ore;

import mod.badores.BadOres;
import mod.badores.oremanagement.BlockInfo;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;

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

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		BlockInfo blockInfo = BadOres.oreManager.getBlockInfo(this);
		Chunk chunk = world.getChunkFromChunkCoords(chunkX, chunkZ);

		int bX = chunkX * 16;
		int bZ = chunkZ * 16;
		int bXE = bX + 16;
		int bZE = bZ + 16;
		for (int y = 45; y < 48; ++y) {
			for (int x = 0; x < 16; ++x) {
				for (int z = 0; z < 16; ++z) {
					if (rand.nextInt(2) == 0 && chunk.getBlock(x, y, z) == Blocks.stone) {
						chunk.func_150807_a(x, y, z, blockInfo.block, blockInfo.metadata);
					}
				}
			}
		}
	}
}
