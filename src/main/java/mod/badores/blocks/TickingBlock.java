package mod.badores.blocks;

import net.minecraft.world.World;

/**
 * @author diesieben07
 */
public interface TickingBlock {

    void tick(World world, int x, int y, int z);

}
