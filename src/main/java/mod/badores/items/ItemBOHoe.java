package mod.badores.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mod.badores.oremanagement.BadOre;
import mod.badores.oremanagement.ToolType;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;

/**
 * @author diesieben07
 */
public class ItemBOHoe extends ItemHoe {

	private final BadOre ore;

	public ItemBOHoe(ToolMaterial toolMaterial, BadOre ore) {
		super(toolMaterial);
		this.ore = ore;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getItemStackDisplayName(ItemStack stack) {
		return ore.getDisplayName(ToolType.HOE);
	}
}
