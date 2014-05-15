package mod.badores.ore;

import cpw.mods.fml.relauncher.Side;
import mod.badores.BadOres;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author diesieben07
 */
public abstract class AbstractOre implements BadOre {

    protected final Random rand = new Random();

	@Override
	public boolean canMakeTools() {
		return false;
	}

	@Override
	public ToolInfo getToolInfo() {
		throw new UnsupportedOperationException();
	}

	@Override
    public void onHarvest(EntityPlayer miner, World world, int x, int y, int z, Side side) { }

	@Override
	public void onRemove(EntityPlayer miner, World world, int x, int y, int z, Side side) { }

	@Override
    public String getIconName() {
        return BadOres.MOD_ID + ":badOre_" + getName();
    }

    @Override
    public List<ItemStack> getDroppedItems(World world, int x, int y, int z, Block block, int meta, int fortune) {
        return Arrays.asList(new ItemStack(block, 1, meta));
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider, BlockInfo blockInfo) {
        BiomeGenBase biomeGenBase = world.getBiomeGenForCoords(chunkX + 16, chunkZ + 16);

        int veinsGenerated = getGeneratedVeinsInBiome(biomeGenBase, random);

        for (int i = 0; i < veinsGenerated; i++) {
            int veinSize = getVeinSize(biomeGenBase, random);

            if (veinSize > 0)
            {
                Pair<Integer, Integer> minMax = getMinMaxGenerationY(biomeGenBase, random);
                WorldGenMinable worldGenMinable = new WorldGenMinable(blockInfo.block, blockInfo.metadata, veinSize, getDestinationBlock(biomeGenBase, random));

                int x = chunkX + random.nextInt(16);
                int y = random.nextInt(minMax.getRight() - minMax.getLeft() + 1) + minMax.getLeft();
                int z = chunkZ + random.nextInt(16);

                worldGenMinable.generate(world, random, x, y, z);
            }
        }
    }

    public int getGeneratedVeinsInBiome(BiomeGenBase biomeGenBase, Random random)
    {
        return 1;
    }

    public int getVeinSize(BiomeGenBase biomeGenBase, Random random)
    {
        return 5;
    }

    public Block getDestinationBlock(BiomeGenBase biomeGenBase, Random random)
    {
        return Blocks.stone;
    }

    public Pair<Integer, Integer> getMinMaxGenerationY(BiomeGenBase biomeGenBase, Random random)
    {
        return new ImmutablePair<Integer, Integer>(0, 64);
    }
}
