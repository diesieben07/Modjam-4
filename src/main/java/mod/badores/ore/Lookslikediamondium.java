package mod.badores.ore;

import mod.badores.items.BOOreProduct;
import mod.badores.oremanagement.ArmorInfo;
import mod.badores.oremanagement.OreForm;
import mod.badores.oremanagement.ToolInfo;
import net.minecraft.init.Items;

/**
 * @author diesieben07
 */
public class Lookslikediamondium extends AbstractOre {

	@Override
	public String getName() {
		return "lookslikediamondium";
	}

	@Override
	public boolean hasIngot() {
		return true;
	}

	@Override
	public boolean dropsIngotDirectly() {
		return true;
	}

	@Override
	public boolean hasTools() {
		return true;
	}

	@Override
	public ToolInfo getToolInfo() {
		return new ToolInfo(0, 1, 2.0F, 0.0F, 15);
	}

	@Override
	public boolean hasArmor() {
		return true;
	}

	@Override
	public ArmorInfo getArmorInfo() {
		return new ArmorInfo(1, new int[]{1, 1, 1, 1}, 0);
	}

	@Override
	public String getIngotIconName() {
		return "minecraft:diamond";
	}

	@Override
	public void postProcessItem(BOOreProduct item, OreForm form) {
		switch (form) {
			case AXE:
				item.overrideIcon(Items.diamond_axe);
				break;
			case HOE:
				item.overrideIcon(Items.diamond_hoe);
				break;
			case PICKAXE:
				item.overrideIcon(Items.diamond_pickaxe);
				break;
			case SHOVEL:
				item.overrideIcon(Items.diamond_shovel);
				break;
			case SWORD:
				item.overrideIcon(Items.diamond_sword);
				break;
			case HELMET:
				item.overrideIcon(Items.diamond_helmet);
				break;
			case CHESTPLATE:
				item.overrideIcon(Items.diamond_chestplate);
				break;
			case LEGGINGS:
				item.overrideIcon(Items.diamond_leggings);
				break;
			case BOOTS:
				item.overrideIcon(Items.diamond_boots);
				break;
		}
	}
}
