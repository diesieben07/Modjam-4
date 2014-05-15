package mod.badores.ore;

import net.minecraft.world.biome.BiomeGenBase;

import java.util.Random;

/**
 * @author diesieben07
 */
public class Unobtainium extends AbstractOre {

    @Override
    public float oreHardness() {
        return -18000000.0F;
    }

    @Override
	public String getName() {
		return "unobtainium";
	}
}
