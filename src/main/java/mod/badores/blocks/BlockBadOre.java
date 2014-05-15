package mod.badores.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import mod.badores.BadOres;
import mod.badores.items.ItemBlockMetadata;
import mod.badores.ore.BadOre;
import mod.badores.util.Sides;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.List;

/**
 * @author diesieben07
 */
public class BlockBadOre extends BOBlock {

	private static int instanceCounter = 0;
	private BadOre[] ores = new BadOre[16];

	public BlockBadOre() {
		super(Material.rock);
		setCreativeTab(BadOres.creativeTab);
		setHardness(3.0F);
		setResistance(5.0F);
		setStepSound(soundTypePiston);

		GameRegistry.registerBlock(this, ItemBlockMetadata.class, "badOre" + (instanceCounter++));
	}

	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		for (int i = 0; i < ores.length; ++i) {
			if (ores[i] != null) {
				//noinspection unchecked
				list.add(new ItemStack(item, 1, i));
			}
		}
	}

	public void addOre(int metadata, BadOre ore) {
		ores[metadata] = ore;
	}

	private BadOre getOre(int metadata) {
		return metadata < ores.length ? ores[metadata] : ores[0];
	}

	@Override
	public void onBlockHarvested(World world, int x, int y, int z, int meta, EntityPlayer player) {
		super.onBlockHarvested(world, x, y, z, meta, player);
		getOre(meta).onMined(player, world, x, y, z, Sides.logical(world));
	}

}