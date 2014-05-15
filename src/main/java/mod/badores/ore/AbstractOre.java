package mod.badores.ore;

import cpw.mods.fml.relauncher.Side;
import mod.badores.BadOres;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author diesieben07
 */
public abstract class AbstractOre implements BadOre {

    protected final Random rand = new Random();

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
}
