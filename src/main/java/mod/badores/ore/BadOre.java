package mod.badores.ore;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;

import java.util.List;
import java.util.Random;

/**
 * @author diesieben07
 */
public interface BadOre {

	boolean canMakeTools();

	boolean hasIngot();

	ToolInfo getToolInfo();

    void onHarvest(EntityPlayer miner, World world, int x, int y, int z, Side side);

	void onRemove(EntityPlayer miner, World world, int x, int y, int z, Side side);

    String getName();

    String getIconName();

    List<ItemStack> getDroppedItems(World world, int x, int y, int z, Block block, int meta, int fortune);

	Entity createDropEntity(World world, double x, double y, double z, ItemStack stack);

    void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider, BlockInfo blockInfo);

    float lightLevel();

    int initialTickRate();

    void tick(World world, int x, int y, int z, Block block, Random random, Side side);
}
