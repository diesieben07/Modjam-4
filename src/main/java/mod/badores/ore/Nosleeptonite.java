package mod.badores.ore;

import cpw.mods.fml.relauncher.Side;
import mod.badores.BadOres;
import mod.badores.blocks.BlockTickProvider;
import mod.badores.entities.EntityNosleeptonite;
import mod.badores.oremanagement.BlockInfo;
import mod.badores.util.FakePlayerDetection;
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
    protected int veinsPerChunk(Random r, World w, int chunkX, int chunkZ) {
        return r.nextInt(40) == 0 ? 1 : 0;
    }

    @Override
    protected int veinSize() {
        return 1;
    }

    @Override
    public boolean canTick(boolean isIngotBlock) {
        return !isIngotBlock;
    }

    @Override
    public int initialTickRate(boolean isIngotBlock) {
        return isIngotBlock ? -1 : 1000;
    }

    @Override
    public void tick(World world, int x, int y, int z, Random random, Side side, boolean isIngotBlock, BlockTickProvider tickProvider) {
        super.tick(world, x, y, z, random, side, isIngotBlock, tickProvider);

        if (side.isServer() && rand.nextInt(10) == 0 && !isIngotBlock) {
            world.playSoundEffect(x + 0.5, y + 0.5, z + 0.5, BadOres.MOD_ID + ":nosleeptonite.idle", 1.0f, 1.0f);
        }
    }

    @Override
    public void onHarvest(EntityPlayer miner, World world, int x, int y, int z, Side side, boolean isIngotBlock) {
        if (FakePlayerDetection.isFakePlayer(miner)) return;
        if (side.isServer() && !isIngotBlock) {
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
    public boolean hasIngot() {
        return true;
    }

    @Override
    public String getName() {
        return "nosleeptonite";
    }
}
