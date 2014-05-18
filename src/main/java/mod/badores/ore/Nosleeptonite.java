package mod.badores.ore;

import cpw.mods.fml.relauncher.Side;
import mod.badores.BadOres;
import mod.badores.entities.EntityNosleeptonite;
import mod.badores.oremanagement.BlockInfo;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

import java.util.Random;

/**
 * @author diesieben07
 */
public class Nosleeptonite extends AbstractOre {

    @Override
    protected int genMin(Random random, World world, int chunkX, int chunkZ) {
        return 5;
    }

    @Override
    protected int genMax(Random random, World world, int chunkX, int chunkZ) {
        return 40;
    }

    @Override
    public int initialTickRate() {
        return 1000;
    }

    @Override
	public void tick(World world, int x, int y, int z, Random random, Side side) {
		super.tick(world, x, y, z, random, side);

        if (side.isServer() && rand.nextInt(10) == 0) {
            world.playSoundEffect(x + 0.5, y + 0.5, z + 0.5, BadOres.MOD_ID + ":nosleeptonite.idle", 1.0f, 1.0f);
        }
	}

    @Override
    public void onHarvest(EntityPlayer miner, World world, int x, int y, int z, Side side) {
        if (side.isServer()) {
            BlockInfo blockInfo = BadOres.oreManager.getBlockInfo(this);
            EntityNosleeptonite blockEntity = new EntityNosleeptonite(world, blockInfo.block, blockInfo.metadata);
            blockEntity.setPosition(x + 0.5, y, z + 0.5);
            blockEntity.setRevengeTarget(miner);
            blockEntity.setTarget(miner);
            world.spawnEntityInWorld(blockEntity);
            blockEntity.playLivingSound();

            miner.addPotionEffect(new PotionEffect(Potion.blindness.getId(), 400, 0));
        }
    }

    @Override
	public String getName() {
		return "nosleeptonite";
	}
}
