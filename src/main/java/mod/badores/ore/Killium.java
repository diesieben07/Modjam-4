package mod.badores.ore;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

/**
 * @author diesieben07
 */
public class Killium extends AbstractOre {

	private static final DamageSource killiumDamage = new DamageSource("badores.killium").setDamageBypassesArmor().setDamageIsAbsolute();

	@Override
	public void onHarvest(EntityPlayer miner, World world, int x, int y, int z, Side side) {
		if (rand.nextInt(5) == 0)
			miner.attackEntityFrom(killiumDamage, 10000.0f);
	}

	@Override
	public String getName() {
		return "killium";
	}

}
