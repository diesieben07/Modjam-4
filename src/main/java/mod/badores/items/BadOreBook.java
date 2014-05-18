package mod.badores.items;

import mod.badores.BadOres;
import mod.badores.util.Sides;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * @author diesieben07
 */
public class BadOreBook extends BOItem {

	public BadOreBook() {
		setCreativeTab(BadOres.creativeTab);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if (Sides.logical(world).isClient()) {
			BadOres.proxy.openBook();
		}
		return stack;
	}
}
