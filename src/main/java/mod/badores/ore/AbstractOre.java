package mod.badores.ore;

import cpw.mods.fml.relauncher.Side;
import mod.badores.BadOres;
import mod.badores.oremanagement.*;
import mod.badores.util.I18n;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
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
	public ToolInfo getToolInfo() {
		throw new UnsupportedOperationException();
	}

    @Override
    public ArmorInfo getArmorInfo() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void onHarvest(EntityPlayer miner, World world, int x, int y, int z, Side side) { }

	@Override
	public void onRemove(EntityPlayer miner, World world, int x, int y, int z, Side side) { }

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
        return BadOres.getTextureName("textures/armor/" + getName() + "." + "layer" + layer);
    }

    @Override
    public List<ItemStack> getDroppedItems(World world, int x, int y, int z, Block block, int meta, int fortune) {
        return Arrays.asList(new ItemStack(block, 1, meta));
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
				int max = getMax(random, world, chunkX, chunkZ);
				WorldGenerator worldGenMinable = createGenerator(random, world, chunkX, chunkZ);

				int x = chunkX + random.nextInt(16);
				int y = random.nextInt(max - min + 1) + min;
				int z = chunkZ + random.nextInt(16);

				worldGenMinable.generate(world, random, x, y, z);
			}
        }
    }

	protected int veinsPerChunk(Random r, World w, int chunkX, int chunkZ) {
		return 1;
	}

	protected int dimension() {
		return 0;
	}

	protected int veinSize() {
		return 5;
	}

	protected int genMin(Random random, World world, int chunkX, int chunkZ) {
		return 0;
	}

	protected int getMax(Random random, World world, int chunkX, int chunkZ) {
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
    public void tick(World world, int x, int y, int z, Block block, Random random, Side side) {
        int tickRate = initialTickRate();
        if (tickRate >= 0)
            world.scheduleBlockUpdate(x, y, z, block, tickRate);
    }

    @Override
    public float oreHardness() {
        return 3.0f;
    }

    @Override
    public float getExplosionResistance() {
        return oreHardness() / 5.0f;
    }

    @Override
    public AxisAlignedBB selectionBB() {
        return AxisAlignedBB.getAABBPool().getAABB(0, 0, 0, 1, 1, 1);
    }
}
