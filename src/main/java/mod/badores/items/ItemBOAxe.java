package mod.badores.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mod.badores.oremanagement.BadOre;
import mod.badores.oremanagement.ToolType;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;

/**
 * @author diesieben07
 */
public class ItemBOAxe extends ItemAxe {

	private final BadOre ore;

	public ItemBOAxe(ToolMaterial material, BadOre ore) {
		super(material);
		this.ore = ore;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getItemStackDisplayName(ItemStack stack) {
		return ore.getDisplayName(ToolType.AXE);
	}
}
