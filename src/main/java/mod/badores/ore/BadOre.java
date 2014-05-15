package mod.badores.ore;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.List;

/**
 * @author diesieben07
 */
public interface BadOre {

	boolean canMakeTools();

	ToolInfo getToolInfo();

    void onHarvest(EntityPlayer miner, World world, int x, int y, int z, Side side);

	void onRemove(EntityPlayer miner, World world, int x, int y, int z, Side side);

    String getName();

    String getIconName();

    List<ItemStack> getDroppedItems(World world, int x, int y, int z, Block block, int meta, int fortune);
}
