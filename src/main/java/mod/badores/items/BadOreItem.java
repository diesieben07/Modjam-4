package mod.badores.items;

import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * @author diesieben07
 */
public interface BadOreItem {

	void onContainerTick(Container c, Slot slot, ItemStack stack);

}
