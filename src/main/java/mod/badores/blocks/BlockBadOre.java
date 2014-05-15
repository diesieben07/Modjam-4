package mod.badores.blocks;

import com.google.common.collect.Lists;
import cpw.mods.fml.common.registry.GameRegistry;
import mod.badores.BadOres;
import mod.badores.items.ItemBlockBadOre;
import mod.badores.ore.BadOre;
import mod.badores.util.Sides;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.ArrayList;
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

		GameRegistry.registerBlock(this, ItemBlockBadOre.class, "badOre" + (instanceCounter++));
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

    public String getUnlocalizedName(int meta) {
        return "tile.badores." + getOre(meta).getName();
    }

    @Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        ArrayList<ItemStack> result = Lists.newArrayListWithCapacity(1);
        result.add(new ItemStack(this, 1, metadata));
        return result;
    }

    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {
        blockIcon = iconRegister.registerIcon(BadOres.MOD_ID + ":" + "oreGeneric");

        for (int i = 0; i < ores.length; ++i) {
            if (ores[i] != null) {
                iconRegister.registerIcon(ores[i].getIconName());
            }
        }
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        return super.getIcon(side, meta);

    }
}
