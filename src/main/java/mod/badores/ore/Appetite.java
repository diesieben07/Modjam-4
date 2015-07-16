package mod.badores.ore;

import cpw.mods.fml.relauncher.Side;
import mod.badores.util.FakePlayerDetection;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

/**
 * @author diesieben07
 */
public class Appetite extends AbstractOre {

    @Override
    public void addOreDrops(World world, int x, int y, int z, int meta, int fortune, List<ItemStack> drops) {

    }

    @Override
    public void onHarvest(EntityPlayer miner, World world, int x, int y, int z, Side side, boolean isIngotBlock) {
        if (FakePlayerDetection.isFakePlayer(miner)) return;
		if (side.isServer())
			miner.getFoodStats().addStats(4, 0.2f);
	}

    @Override
    public float getHardness(World world, int x, int y, int z, boolean isIngotBlock) {
        return 0.5f;
    }

    @Override
    protected int genMin(Random random, World world, int chunkX, int chunkZ) {
        return 64;
    }

    @Override
    protected int genMax(Random random, World world, int chunkX, int chunkZ) {
        return 128;
    }

    @Override
    protected Block replace() {
        return Blocks.sand;
    }

    @Override
    public int harvestLevelRequired(boolean isIngotBlock) {
        return -1;
    }

    @Override
    public String toolRequired(boolean isIngotBlock) {
        return "shovel";
    }

    @Override
	public String getName() {
		return "appetite";
	}
}
