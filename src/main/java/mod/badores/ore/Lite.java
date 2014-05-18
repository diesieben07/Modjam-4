package mod.badores.ore;

import net.minecraft.world.World;

import java.util.Random;

/**
 * @author diesieben07
 */
public class Lite extends AbstractOre {

    @Override
    protected int veinSize() {
        return 2;
    }

    @Override
    protected int veinsPerChunk(Random r, World w, int chunkX, int chunkZ) {
        return 4 + r.nextInt(8);
    }

    @Override
	public int lightLevel(boolean isIngotBlock) {
		return 4;
	}

	@Override
	public String getName() {
		return "lite";
	}
}
