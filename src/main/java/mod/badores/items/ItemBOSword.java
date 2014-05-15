package mod.badores.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mod.badores.oremanagement.BadOre;
import mod.badores.oremanagement.ToolType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

/**
 * @author diesieben07
 */
public class ItemBOSword extends ItemSword {

	private final BadOre ore;

	public ItemBOSword(ToolMaterial toolMaterial, BadOre ore) {
		super(toolMaterial);
		this.ore = ore;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getItemStackDisplayName(ItemStack stack) {
		return ore.getDisplayName(ToolType.SWORD);
	}

}
