package mod.badores.ore;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

/**
 * @author diesieben07
 */
public class Wannafite extends AbstractOre {

	// TODO: localize this
	private static final DamageSource wannafiteDamage = new DamageSource("badores.wannafite");

	@Override
	public void onHarvest(EntityPlayer miner, World world, int x, int y, int z, Side side) {
		miner.attackEntityFrom(wannafiteDamage, 4);
	}

	@Override
	public String getName() {
		return "wannafite";
	}

}
