package mod.badores.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mod.badores.ore.BadOre;
import mod.badores.ore.OreManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

/**
 * @author diesieben07
 */
public class ItemBOArmor extends ItemArmor {

    public OreManager.ArmorType omArmorType;
    public BadOre ore;

	public ItemBOArmor(ArmorMaterial material, OreManager.ArmorType armorType, BadOre ore) {
		super(material, 0, armorType.armorType);
        this.omArmorType = armorType;
        this.ore = ore;
	}

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
        return ore.getArmorIconName(slot, type);
    }

	@Override
	@SideOnly(Side.CLIENT)
	public String getItemStackDisplayName(ItemStack stack) {
		return I18n.format("badores." + omArmorType.name, StatCollector.translateToLocal("badores." + ore.getName()));
	}

}
