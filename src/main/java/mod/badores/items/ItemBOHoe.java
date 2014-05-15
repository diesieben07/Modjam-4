package mod.badores.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mod.badores.ore.BadOre;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

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
		return I18n.format("badores.hoe", StatCollector.translateToLocal("badores." + ore.getName()));
	}
}
