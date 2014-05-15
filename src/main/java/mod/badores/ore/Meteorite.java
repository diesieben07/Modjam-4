package mod.badores.ore;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

/**
 * @author diesieben07
 */
public class Meteorite extends AbstractOre {

    public static final int METEORITE__SPAWN_SIDE = 50;

	@Override
	public void onHarvest(EntityPlayer miner, World world, int x, int y, int z, Side side) {
        if (side.isServer()) {
            int number = rand.nextInt(20) + 3;
            Block fallingBlock = rand.nextBoolean() ? Blocks.stone : Blocks.netherrack;
            for (int i = 0; i < number; i++) {
                double spawnX = x + METEORITE__SPAWN_SIDE * (rand.nextDouble() - rand.nextDouble());
                double spawnY = 260.0;
                double spawnZ = x + METEORITE__SPAWN_SIDE * (rand.nextDouble() - rand.nextDouble());
                EntityFallingBlock entityFallingBlock = new EntityFallingBlock(world, spawnX, spawnY, spawnZ, fallingBlock);
                entityFallingBlock.field_145812_b = 2;
                entityFallingBlock.motionX = rand.nextDouble() - rand.nextDouble();
                entityFallingBlock.motionZ = rand.nextDouble() - rand.nextDouble();
                world.spawnEntityInWorld(entityFallingBlock);
            }
        }
	}

    @Override
    public String getName() {
        return "meteorite";
    }

}
