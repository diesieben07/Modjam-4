package mod.badores.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mod.badores.ore.AbstractOre;
import mod.badores.oremanagement.BadOre;
import mod.badores.oremanagement.ToolType;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

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
	public boolean onBlockDestroyed(ItemStack stack, World world, Block block, int x, int y, int z, EntityLivingBase living) {
		AbstractOre.invokeOnMine(ore, ToolType.PICKAXE, living, block, world, x, y, z);
		return super.onBlockDestroyed(stack, world, block, x, y, z, living);
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase player, EntityLivingBase target) {
		AbstractOre.invokeOnAttack(ore, ToolType.PICKAXE, player, target);
		return super.hitEntity(stack, player, target);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getItemStackDisplayName(ItemStack stack) {
		return ore.getDisplayName(ToolType.PICKAXE);
	}

}
