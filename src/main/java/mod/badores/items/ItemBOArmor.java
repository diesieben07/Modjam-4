package mod.badores.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mod.badores.oremanagement.ArmorType;
import mod.badores.oremanagement.BadOre;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

/**
 * @author diesieben07
 */
public class ItemBOArmor extends ItemArmor {

    public ArmorType omArmorType;
    public BadOre ore;

	public ItemBOArmor(ArmorMaterial material, ArmorType armorType, BadOre ore) {
		super(material, 0, armorType.vanillaID);
        this.omArmorType = armorType;
        this.ore = ore;
	}

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
        return ore.getArmorIconName(omArmorType);
    }

	@Override
	@SideOnly(Side.CLIENT)
	public String getItemStackDisplayName(ItemStack stack) {
		return ore.getDisplayName(omArmorType);
	}

}
