package mod.badores.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mod.badores.ore.AbstractOre;
import mod.badores.oremanagement.ArmorType;
import mod.badores.oremanagement.BadOre;
import mod.badores.oremanagement.OreForm;
import mod.badores.util.Sides;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

/**
 * @author diesieben07
 */
public class ItemBOArmor extends ItemArmor implements BOOreProduct {

	public ArmorType omArmorType;
	public BadOre ore;
	private Item overriddenIcon;

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

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
		ore.onArmorTick(omArmorType, player, world, Sides.logical(world), 36 + (3 - omArmorType.vanillaID), itemStack);
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity player, int slot, boolean inHotbar) {
		super.onUpdate(stack, world, player, slot, inHotbar);
		AbstractOre.invokeInventoryTick(ore, OreForm.fromArmor(omArmorType), stack, slot, world, player);
	}

	@Override
	public IIcon getIconFromDamage(int metadata) {
		return overriddenIcon == null ? super.getIconFromDamage(metadata) : overriddenIcon.getIconFromDamage(metadata);
	}

	@Override
	public void registerIcons(IIconRegister iconRegister) {
		if (overriddenIcon == null) {
			super.registerIcons(iconRegister);
		}
	}

	@Override
	public void overrideIcon(Item model) {
		overriddenIcon = model;
	}

}
