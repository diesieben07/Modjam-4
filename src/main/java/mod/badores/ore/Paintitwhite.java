package mod.badores.ore;

import mod.badores.oremanagement.BlockInfo;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.Arrays;
import java.util.List;

/**
 * @author diesieben07
 */
public class Paintitwhite extends AbstractOre {

	@Override
	public String getName() {
		return "paintitwhite";
	}

	@Override
	public List<ItemStack> getDroppedItems(World world, int x, int y, int z, int meta, int fortune) {
		return Arrays.asList(new ItemStack(Items.dye, 1, 15));
	}
}
