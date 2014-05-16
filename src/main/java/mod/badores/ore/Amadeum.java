package mod.badores.ore;

import cpw.mods.fml.relauncher.Side;
import mod.badores.oremanagement.BlockInfo;
import net.minecraft.world.World;

import java.util.Random;

/**
 * @author diesieben07
 */
public class Amadeum extends AbstractOre {

	@Override
	protected int veinsPerChunk(Random r, World w, int chunkX, int chunkZ) {
		return r.nextInt(100) == 0 ? 1 : 0;
	}

	@Override
	public int initialTickRate() {
		return rand.nextInt(6) + 18;
	}

	private static final String[] soundNames = {
			"harp", "bd", "snare", "hat", "bassattack"
	};

	@Override
	public void tick(World world, int x, int y, int z, BlockInfo blockInfo, Random random, Side side) {
		super.tick(world, x, y, z, blockInfo, random, side);

		if (side.isServer()) {
			int soundId = random.nextInt(5);
			int note = random.nextInt(25);

			float f = (float) Math.pow(2.0D, (note - 12) / 12.0D);
			String soundName = soundNames[soundId];

			world.playSoundEffect((double) x + 0.5D, (double) y + 0.5D, (double) z + 0.5D, "note." + soundName, 3.0F, f);
//            world.spawnParticle("note", (double)x + 0.5D, (double)y + 1.2D, (double)z + 0.5D, (double)note / 24.0D, 0.0D, 0.0D);
		}
	}

	@Override
	public String getName() {
		return "amadeum";
	}
}
