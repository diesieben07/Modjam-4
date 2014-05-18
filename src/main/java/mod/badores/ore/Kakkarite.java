package mod.badores.ore;

import mod.badores.items.ItemBOIngot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

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
        return 64;
    }

    @Override
	public void addDroppedItemsToList(World world, int x, int y, int z, int meta, int fortune, List<ItemStack> drops) {
	    int items = 9001;
	    while (items > 0) {
		    ItemStack stack = ItemBOIngot.createIngot(this);
		    stack.stackSize = Math.min(64, items);
		    items -= stack.stackSize;
		    drops.add(stack);
	    }
	}
}
