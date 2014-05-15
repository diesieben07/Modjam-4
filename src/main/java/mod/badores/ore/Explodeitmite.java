package mod.badores.ore;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import java.util.Random;

/**
 * @author diesieben07
 */
public class Explodeitmite extends AbstractOre {

    @Override
    public int initialTickRate() {
        return 100;
    }

    @Override
    public void tick(World world, int x, int y, int z, Block block, Random random, Side side) {
        super.tick(world, x, y, z, block, random, side);

        if (side.isServer())
        {
            if (rand.nextInt(200) == 0)
                world.createExplosion(null, x + 0.5, y + 0.5, z + 0.5, 2.0f + rand.nextFloat() * 3.0f, false);
        }
    }

    @Override
    public void onRemove(EntityPlayer miner, World world, int x, int y, int z, Side side) {
        if (rand.nextInt(4) == 0)
            world.createExplosion(null, x + 0.5, y + 0.5, z + 0.5, 2.0f + rand.nextFloat() * 3.0f, false);
    }

    @Override
    public float oreHardness() {
        return 8.0f;
    }

    @Override
    public float getExplosionResistance() {
        return 10.0f;
    }

    @Override
    public String getName() {
        return "explodeitmite";
    }
}
