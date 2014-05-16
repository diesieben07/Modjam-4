package mod.badores.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mod.badores.ore.AbstractOre;
import mod.badores.oremanagement.ArmorType;
import mod.badores.oremanagement.BadOre;
import mod.badores.oremanagement.OreForm;
import mod.badores.util.Sides;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

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

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
		ore.onArmorTick(omArmorType, player, world, Sides.logical(world));
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity player, int slot, boolean inHotbar) {
		super.onUpdate(stack, world, player, slot, inHotbar);
		AbstractOre.invokeInventoryTick(ore, OreForm.fromArmor(omArmorType), stack, world, player);
	}

}
