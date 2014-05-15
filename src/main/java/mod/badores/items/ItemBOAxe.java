package mod.badores.items;

import mod.badores.ore.BadOre;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

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
	public String getItemStackDisplayName(ItemStack stack) {
		return I18n.format("badores.axe", StatCollector.translateToLocal("badores." + ore.getName()));
	}
}
