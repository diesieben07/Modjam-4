package mod.badores.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mod.badores.oremanagement.BadOre;
import mod.badores.oremanagement.ToolType;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;

/**
 * @author diesieben07
 */
public class ItemBOPickaxe extends ItemPickaxe {

	private final BadOre ore;

	public ItemBOPickaxe(ToolMaterial material, BadOre ore) {
		super(material);
		this.ore = ore;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getItemStackDisplayName(ItemStack stack) {
		return ore.getDisplayName(ToolType.PICKAXE);
	}

}
