package mod.badores.ore;

import cpw.mods.fml.relauncher.Side;
import mod.badores.BadOres;
import mod.badores.items.BOOreProduct;
import mod.badores.items.ItemBOIngot;
import mod.badores.oremanagement.*;
import mod.badores.util.I18n;
import mod.badores.util.Sides;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author diesieben07
 */
public abstract class AbstractOre implements BadOre {

	protected final Random rand = new Random();

	public static void invokeOnMine(BadOre ore, ToolType type, EntityLivingBase living, Block block, World world, int x, int y, int z) {
		if (living instanceof EntityPlayer) {
			ore.onToolMine(type, ((EntityPlayer) living), world, x, y, z, block, Sides.logical(world));
		}
	}

	public static void invokeOnAttack(BadOre ore, ToolType type, EntityLivingBase living, EntityLivingBase target) {
		if (living instanceof EntityPlayer) {
			ore.onToolEntityAttack(type, ((EntityPlayer) living), target, living.worldObj, Sides.logical(living.worldObj));
		}
	}

	public static void invokeInventoryTick(BadOre ore, OreForm form, ItemStack stack, int slot, World world, Entity player) {
		if (player instanceof EntityPlayer) {
			ore.onInventoryTick(form, stack, slot, ((EntityPlayer) player), world, Sides.logical(world));
		}
	}

	@Override
	public boolean hasTools() {
		return false;
	}

	@Override
	public boolean hasArmor() {
		return false;
	}

	@Override
	public boolean hasIngot() {
		return false;
	}

	@Override
	public boolean dropsIngotDirectly() {
		return false;
	}

	@Override
	public void postProcessItem(BOOreProduct item, OreForm form) { }

	@Override
	public float getSmeltingXP() {
		return 0.5f;
	}

	@Override
	public ToolInfo getToolInfo() {
		throw new UnsupportedOperationException();
	}

	@Override
	public ArmorInfo getArmorInfo() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void onHarvest(EntityPlayer miner, World world, int x, int y, int z, Side side) {
	}

	@Override
	public void onRemove(EntityPlayer miner, World world, int x, int y, int z, Side side) {
	}

	@Override
	public String getIconName() {
		return BadOres.getTextureName(getName() + ".ore");
	}

	@Override
	public String getIngotIconName() {
		return BadOres.MOD_ID + ":" + getName() + ".ingot";
	}

	@Override
	public String getDisplayName() {
		return I18n.translateBO(getName());
	}

	@Override
	public String getDisplayName(OreSubName name) {
		return name.subName(I18n.translateBO(getName()));
	}

	@Override
	public String getArmorIconName(ArmorType type) {
		int layer = type.getLayer();
		return BadOres.getTextureName("textures/armor/" + getName() + "." + "layer" + layer + ".png");
	}

	@Override
	public List<ItemStack> getDroppedItems(World world, int x, int y, int z, int meta, int fortune) {
		if (dropsIngotDirectly()) {
			return Arrays.asList(ItemBOIngot.createIngot(this));
		} else {
			return Arrays.asList(BadOres.oreManager.getBlockInfo(this).asStack());
		}
	}

	@Override
	public Entity createDropEntity(World world, double x, double y, double z, ItemStack stack) {
		EntityItem entity = new EntityItem(world, x, y, z, stack);
		entity.delayBeforeCanPickup = 10;
		return entity;
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		int numVeins = veinsPerChunk(random, world, chunkX, chunkZ);

		for (int i = 0; i < numVeins; i++) {
			int veinSize = veinSize();

			if (veinSize > 0) {
				int min = genMin(random, world, chunkX, chunkZ);
				int max = genMax(random, world, chunkX, chunkZ);
				WorldGenerator worldGenMinable = createGenerator(random, world, chunkX, chunkZ);

				int x = chunkX * 16 + random.nextInt(16);
				int y = random.nextInt(max - min + 1) + min;
				int z = chunkZ * 16 + random.nextInt(16);

				worldGenMinable.generate(world, random, x, y, z);
			}
		}
	}

	protected int veinsPerChunk(Random r, World w, int chunkX, int chunkZ) {
		return r.nextInt(4);
	}

	protected int dimension() {
		return 0;
	}

	protected int veinSize() {
		return 8;
	}

	protected int genMin(Random random, World world, int chunkX, int chunkZ) {
		return 0;
	}

	protected int genMax(Random random, World world, int chunkX, int chunkZ) {
		return 64;
	}

	protected Block replace() {
		switch (dimension()) {
			case 0:
				return Blocks.stone;
			case 1:
				return Blocks.end_stone;
			case -1:
				return Blocks.netherrack;
			default:
				throw new IllegalStateException("Unknown dimension " + dimension() + ", please override replace() instead!");
		}
	}

	private WorldGenMinable generator;

	protected WorldGenMinable createGenerator(Random r, World w, int chunkX, int chunkZ) {
		if (generator == null) {
			BlockInfo block = BadOres.oreManager.getBlockInfo(this);
			generator = new WorldGenMinable(block.block, block.metadata, veinSize(), replace());
		}
		return generator;
	}

	@Override
	public int lightLevel() {
		return 0;
	}

	@Override
	public int initialTickRate() {
		return -1;
	}

	@Override
	public void tick(World world, int x, int y, int z, BlockInfo blockInfo, Random random, Side side) {
		int tickRate = initialTickRate();
		if (tickRate >= 0)
			world.scheduleBlockUpdate(x, y, z, blockInfo.block, tickRate);
	}

	@Override
	public float getHardness(World world, int x, int y, int z) {
		return 3.0f;
	}

	@Override
	public float getExplosionResistance(World world, int x, int y, int z) {
		return getHardness(world, x, y, z) / 5.0f;
	}

	@Override
	public boolean shouldSelectionRayTrace() {
		return true;
	}

	@Override
	public void onToolMine(ToolType type, EntityPlayer player, World world, int x, int y, int z, Block block, Side side) { }

	@Override
	public void onToolEntityAttack(ToolType type, EntityPlayer player, EntityLivingBase target, World world, Side side) { }

	@Override
	public void onInventoryTick(OreForm form, ItemStack stack, int slot, EntityPlayer player, World world, Side side) { }

	@Override
	public void onArmorTick(ArmorType type, EntityPlayer player, World world, Side side, int slot, ItemStack stack) { }

    @Override
    public void onArmorAttacked(ArmorType type, EntityPlayer player, DamageSource damageSource, float amount, World world, Side side) {

    }
}
