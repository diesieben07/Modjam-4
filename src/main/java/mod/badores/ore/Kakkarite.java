package mod.badores.ore;

import mod.badores.items.ItemBOIngot;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.Arrays;
import java.util.List;

/**
 * @author diesieben07
 */
public class Kakkarite extends AbstractOre {

	@Override
	public String getName() {
		return "kakkarite";
	}

    @Override
    public boolean hasIngot() {
        return true;
    }

    @Override
    public boolean dropsIngotDirectly() {
        return true;
    }

    @Override
    public int ingotStackSize() {
        return 9000;
    }

    @Override
	public List<ItemStack> getDroppedItems(World world, int x, int y, int z, int meta, int fortune) {
        ItemStack stack = ItemBOIngot.createIngot(this);
        stack.stackSize = 9001;
        return Arrays.asList(stack);
	}
}
