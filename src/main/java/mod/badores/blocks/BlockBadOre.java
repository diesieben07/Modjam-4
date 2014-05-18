package mod.badores.blocks;

import com.google.common.collect.Lists;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import mod.badores.BadOres;
import mod.badores.BlockTicker;
import mod.badores.items.ItemBlockBadOre;
import mod.badores.oremanagement.BadOre;
import mod.badores.oremanagement.OreForm;
import mod.badores.util.Sides;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * @author diesieben07
 */
public class BlockBadOre extends BOBlock implements ITileEntityProvider {

	public static final int MAX_ORES_PER_BLOCK = 8;
	private static final int ORE_MASK = 0b0111;
	private static final int INGOT_BLOCK = 0b1000;

	private static int instanceCounter = 0;
	private BadOre[] ores = new BadOre[MAX_ORES_PER_BLOCK];

	private IIcon[] oreIcons = new IIcon[MAX_ORES_PER_BLOCK];
	private IIcon[] ingotBlockIcons = new IIcon[MAX_ORES_PER_BLOCK];

	public BlockBadOre() {
		super(Material.rock);
		setCreativeTab(BadOres.creativeTab);
		setResistance(5.0F);
		setStepSound(soundTypePiston);

		GameRegistry.registerBlock(this, ItemBlockBadOre.class, "badOre" + (instanceCounter++));
	}

	public static ItemStack createOre(BadOre ore) {
		return BadOres.oreManager.getBlockInfo(ore).asStack();
	}

	public static ItemStack createIngotBlock(BadOre ore) {
		checkArgument(ore.hasIngot() && ore.hasIngotBlock());
		ItemStack stack = BadOres.oreManager.getBlockInfo(ore).asStack();
		stack.setItemDamage(stack.getItemDamage() | INGOT_BLOCK);
		return stack;
	}

	public static OreForm getOreForm(ItemStack stack) {
		return isIngotBlock(stack) ? OreForm.INGOT_BLOCK : OreForm.ORE;
	}

	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		@SuppressWarnings("unchecked")
		List<ItemStack> stacks = list;
		for (BadOre ore : ores) {
			if (ore != null) {
				stacks.add(createOre(ore));
				if (ore.hasIngotBlock()) {
					stacks.add(createIngotBlock(ore));
				}
			}
		}
	}

    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
        return BadOres.oreManager.getBlockInfo(getOre(world.getBlockMetadata(x, y, z))).asStack();
    }

	public static boolean isIngotBlock(ItemStack stack) {
		return isIngotBlock(stack.getItemDamage());
	}

	public static boolean isIngotBlock(int meta) {
		return (meta & INGOT_BLOCK) != 0;
	}

    public void addOre(int metadata, BadOre ore) {
		ores[metadata] = ore;
	}

	public BadOre getOre(int metadata) {
		BadOre ore = ores[(metadata & ORE_MASK)];
		return ore == null ? ores[0] : ore;
	}

	public BadOre getOre(ItemStack stack) {
		return getOre(stack.getItemDamage());
	}

    @Override
	public void harvestBlock(World world, EntityPlayer player, int x, int y, int z, int metadata) {
		super.harvestBlock(world, player, x, y, z, metadata);
		// side is always server
		getOre(metadata).onHarvest(player, world, x, y, z, Side.SERVER, isIngotBlock(metadata));
	}

	// evil hack, kids don't try this at home
	private int dropMeta = -1;

	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
		dropMeta = metadata;
		ArrayList<ItemStack> result = Lists.newArrayList();
		getOre(metadata).addDrops(world, x, y, z, metadata, fortune, result, isIngotBlock(metadata));
		return result;
	}

	@Override
	public boolean removedByPlayer(World world, EntityPlayer player, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
		getOre(meta).onRemove(player, world, x, y, z, Sides.logical(world), isIngotBlock(meta));
		return super.removedByPlayer(world, player, x, y, z);
	}

	@Override
	protected void dropBlockAsItem(World world, int x, int y, int z, ItemStack stack) {
		if (!world.isRemote && world.getGameRules().getGameRuleBooleanValue("doTileDrops")) {
			float f = 0.7F;
			double eX = x + (world.rand.nextFloat() * f) + (1.0F - f) * 0.5D;
			double eY = y + (world.rand.nextFloat() * f) + (1.0F - f) * 0.5D;
			double eZ = z + (world.rand.nextFloat() * f) + (1.0F - f) * 0.5D;
			world.spawnEntityInWorld(getOre(dropMeta).createDropEntity(world, eX, eY, eZ, stack, isIngotBlock(dropMeta)));
		}
	}

	@Override
	public void registerBlockIcons(IIconRegister iconRegister) {
		blockIcon = iconRegister.registerIcon(BadOres.MOD_ID + ":" + "oreGeneric");

		for (int i = 0; i < ores.length; ++i) {
			BadOre ore = ores[i];
			if (ore != null) {
				oreIcons[i] = iconRegister.registerIcon(ore.getIconName());
				if (ore.hasIngot() && ore.hasIngotBlock()) {
					ingotBlockIcons[i] = iconRegister.registerIcon(ore.getIngotBlockIcon());
				}
			}
		}
	}

	@Override
	public void onBlockAdded(World world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
        int tickRate = getOre(meta).initialTickRate(isIngotBlock(meta));

        if (tickRate >= 0) {
            TileEntity tileEntity = world.getTileEntity(x, y, z);
            ((TileEntityBadOre) tileEntity).scheduleTick(tickRate);
        }
    }

	@Override
	public float getBlockHardness(World world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
		BadOre ore = getOre(meta);
		return ore.getHardness(world, x, y, z, isIngotBlock(meta));
	}

	@Override
	public IIcon getIcon(int side, int meta) {
		IIcon i;
		if (isIngotBlock(meta)) {
			i =  ingotBlockIcons[meta & ORE_MASK];
		} else {
			i = oreIcons[meta & ORE_MASK];
		}
		return i == null ? blockIcon : i;
	}

	@Override
	public int getLightValue(IBlockAccess world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
		return getOre(meta).lightLevel(isIngotBlock(meta));
	}

	@Override
	public float getExplosionResistance(Entity entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ) {
		int meta = world.getBlockMetadata(x, y, z);
		return getOre(meta).getExplosionResistance(world, x, y, z, isIngotBlock(meta));
	}

	@Override
	public MovingObjectPosition collisionRayTrace(World world, int x, int y, int z, Vec3 ray, Vec3 ray2) {
		int meta = world.getBlockMetadata(x, y, z);
		if (getOre(meta).shouldSelectionRayTrace(isIngotBlock(meta)))
			return super.collisionRayTrace(world, x, y, z, ray, ray2);
		return null;
	}

    @Override
    public String getHarvestTool(int metadata) {
        return getOre(metadata).toolRequired(isIngotBlock(metadata));
    }

    @Override
    public int getHarvestLevel(int metadata) {
        return getOre(metadata).harvestLevelRequired(isIngotBlock(metadata));
    }

    public boolean needsTE(BadOre ore) {
        return ore.canTick();
    }

    @Override
    public boolean hasTileEntity(int metadata) {
        return needsTE(getOre(metadata));
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        if (needsTE(getOre(meta)))
            return new TileEntityBadOre();

        return null;
    }
}
