package mod.badores.ore;

import cpw.mods.fml.relauncher.Side;
import mod.badores.BadOres;
import mod.badores.network.PacketRandomTranslation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;

/**
 * @author diesieben07
 */
public class Polite extends AbstractOre {

	@Override
	public void onHarvest(EntityPlayer miner, World world, int x, int y, int z, Side side) {
		if (side.isServer()) {
			BadOres.network.sendTo(new PacketRandomTranslation("badores.polite"), (EntityPlayerMP) miner);
		}
	}

	@Override
	public String getName() {
		return "polite";
	}
}
