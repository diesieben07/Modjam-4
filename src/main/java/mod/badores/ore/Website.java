package mod.badores.ore;

import cpw.mods.fml.relauncher.Side;
import mod.badores.BadOres;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * @author diesieben07
 */
public class Website extends AbstractOre {

	@Override
	public void onRemove(EntityPlayer miner, World world, int x, int y, int z, Side side, boolean isIngotBlock) {
		if (side.isClient()) {
			BadOres.proxy.openRandomWebsite();
		}
	}

	@Override
	public String getName() {
		return "website";
	}
}
