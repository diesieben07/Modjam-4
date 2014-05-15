package mod.badores.ore;

import cpw.mods.fml.relauncher.Side;
import mod.badores.oremanagement.BlockInfo;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.Arrays;
import java.util.List;

/**
 * @author diesieben07
 */
public class Stonium extends AbstractOre {

	@Override
	public String getName() {
		return "stonium";
	}

    @Override
    public List<ItemStack> getDroppedItems(World world, int x, int y, int z, BlockInfo blockInfo, int meta, int fortune) {
        return Arrays.asList(new ItemStack(Blocks.stone));
    }
}
