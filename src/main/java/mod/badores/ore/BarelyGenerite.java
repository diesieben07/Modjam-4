package mod.badores.ore;

import net.minecraft.world.World;

import java.util.Random;

/**
 * @author diesieben07
 */
public class BarelyGenerite extends AbstractOre {

    @Override
    public boolean hasIngot() {
        return true;
    }

    @Override
    protected int veinsPerChunk(Random r, World w, int chunkX, int chunkZ) {
        return r.nextInt(10000) == 0 ? 1 : 0;
    }

    @Override
    protected int veinSize() {
        return 1;
    }

    @Override
    public String getName() {
        return "barelyGenerite";
    }
}
