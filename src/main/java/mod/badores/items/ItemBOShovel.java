package mod.badores.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mod.badores.ore.BadOre;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

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
		return I18n.format("badores.shovel", StatCollector.translateToLocal("badores." + ore.getName()));
	}

}
