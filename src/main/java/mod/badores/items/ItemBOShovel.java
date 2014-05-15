package mod.badores.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mod.badores.oremanagement.BadOre;
import mod.badores.oremanagement.ToolType;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;

/**
 * @author diesieben07
 */
public class ItemBOShovel extends ItemSpade {

	private final BadOre ore;

	public ItemBOShovel(ToolMaterial toolMaterial, BadOre ore) {
		super(toolMaterial);
		this.ore = ore;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getItemStackDisplayName(ItemStack stack) {
		return ore.getDisplayName(ToolType.SHOVEL);
	}

}
