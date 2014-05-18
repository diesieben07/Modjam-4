package mod.badores.ore;

import mod.badores.BadOres;
import mod.badores.items.ItemBOIngot;
import mod.badores.oremanagement.OreSubName;
import mod.badores.util.I18n;
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
    public String getDisplayName(OreSubName name) {
        if (name instanceof ItemBOIngot)
            return I18n.translate(getName() + ".ingot.name");
        else
            return super.getDisplayName(name);
    }

    @Override
    public int ingotStackSize() {
        return 64;
    }

    @Override
	public void addOreDrops(World world, int x, int y, int z, int meta, int fortune, List<ItemStack> drops) {
	    int items = 9001;
	    while (items > 0) {
		    ItemStack stack = ItemBOIngot.createIngot(this);
		    stack.stackSize = Math.min(64, items);
		    items -= stack.stackSize;
		    drops.add(stack);
	    }
	}
}
