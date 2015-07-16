package mod.badores.ore;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

import java.util.Random;

/**
 * @author diesieben07
 */
public class Meteorite extends AbstractOre {

    public static final int METEORITE_SPAWN_SIDE = 50;

    @Override
    public void onHarvest(EntityPlayer miner, World world, int x, int y, int z, Side side, boolean isIngotBlock) {
        if (side.isServer()) {
            int number = rand.nextInt(20) + 3;
            Block fallingBlock = rand.nextBoolean() ? Blocks.stone : Blocks.netherrack;
            for (int i = 0; i < number; i++) {
                double spawnX = x + METEORITE_SPAWN_SIDE * (rand.nextDouble() - rand.nextDouble());
                double spawnY = 260.0;
                double spawnZ = z + METEORITE_SPAWN_SIDE * (rand.nextDouble() - rand.nextDouble());
                EntityFallingBlock entity = new EntityFallingBlock(world, spawnX, spawnY, spawnZ, fallingBlock);
                // set age to 2 to prevent entity from vanishing (?)
                entity.field_145812_b = 2;
                entity.motionX = rand.nextDouble() - rand.nextDouble();
                entity.motionZ = rand.nextDouble() - rand.nextDouble();
                world.spawnEntityInWorld(entity);
            }
        }
    }


    @Override
    protected int genMin(Random random, World world, int chunkX, int chunkZ) {
        return 90;
    }

    @Override
    protected int genMax(Random random, World world, int chunkX, int chunkZ) {
        return 256;
    }

    @Override
    public String getName() {
        return "meteorite";
    }

}
