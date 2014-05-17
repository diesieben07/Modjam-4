package mod.badores.ore;

import cpw.mods.fml.relauncher.Side;
import mod.badores.oremanagement.OreForm;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.Random;

/**
 * @author diesieben07
 */
public class Idlikeabite extends AbstractOre {

	@Override
    public void onHarvest(EntityPlayer miner, World world, int x, int y, int z, Side side) {
		if (side.isServer())
			miner.getFoodStats().addExhaustion(rand.nextFloat() * 40.0f);
	}

    @Override
    public void onInventoryTick(OreForm form, ItemStack stack, int slot, EntityPlayer player, World world, Side side) {
        if (side.isServer() && rand.nextInt(200) == 0) {
            player.getFoodStats().addExhaustion(1.0f);
        }
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
        return Blocks.dirt;
    }

    @Override
    public int harvestLevelRequired() {
        return 1;
    }

    @Override
    public String toolRequired() {
        return "shovel";
    }

    @Override
	public String getName() {
		return "idlikeabite";
	}
}
