package mod.badores.ore;

import mod.badores.BadOres;
import mod.badores.config.BOConfig;
import mod.badores.oremanagement.BlockInfo;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;

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
    public int harvestLevelRequired(boolean isIngotBlock) {
        return 0;
    }

    @Override
    public float getHardness(World world, int x, int y, int z, boolean isIngotBlock) {
        return 1.0f;
    }

    @Override
    public void addOreDrops(World world, int x, int y, int z, int meta, int fortune, List<ItemStack> drops) {
        drops.add(new ItemStack(Blocks.cobblestone));
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
        if (!BadOres.config.isOreGenerationDisabled(this)) {
            BlockInfo blockInfo = BadOres.oreManager.getBlockInfo(this);
            Block block = blockInfo.block;
            int meta = blockInfo.metadata;
            Block stone = Blocks.stone;
            Chunk chunk = world.getChunkFromChunkCoords(chunkX, chunkZ);

            // highly optimized (i hope)
            // generate on the 3rd layer in the 4 topmost blocks (16 + 16 = 32; 32 + 12 = 44 => generate on 44 - 47)
            for (int y = 12; y < 16; ++y) {
                for (int x = 0; x < 16; ++x) {
                    for (int z = 0; z < 16; ++z) {
                        ExtendedBlockStorage layer;
                        if (rand.nextInt(2) == 0 && (layer = chunk.getBlockStorageArray()[2]) != null && layer.getBlockByExtId(x, y, z) == stone) {
                            layer.func_150818_a(x, y, z, block);
                            layer.setExtBlockMetadata(x, y, z, meta);
                        }
                    }
                }
            }
        }
    }
}
