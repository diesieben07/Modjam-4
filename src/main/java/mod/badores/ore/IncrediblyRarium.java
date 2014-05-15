package mod.badores.ore;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

import java.util.Random;

/**
 * @author diesieben07
 */
public class IncrediblyRarium extends AbstractOre {

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
		return "incrediblyRarium";
	}
}
