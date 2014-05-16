package mod.badores.ore;

import cpw.mods.fml.relauncher.Side;
import mod.badores.BadOres;
import mod.badores.event.TickEvents;
import mod.badores.network.PacketRandomTranslation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;

/**
 * @author diesieben07
 */
public class Crashium extends AbstractOre {

	@Override
	public void onRemove(final EntityPlayer miner, final World world, int x, int y, int z, Side side) {
		if (side.isServer()) {
			BadOres.network.sendTo(new PacketRandomTranslation("badores.crashium.precrash"), (EntityPlayerMP) miner);

			TickEvents.INSTANCE.schedule(new Runnable() {
				@Override
				public void run() {
					if (world.rand.nextInt(5) == 0) {
						BadOres.network.sendTo(new PacketRandomTranslation("badores.crashium.crash"), (EntityPlayerMP) miner);
						if (!BadOres.devEnv && BadOres.gameBreakingFeatures) {
							TickEvents.INSTANCE.schedule(new Runnable() {
								@Override
								public void run() {
									throw new RuntimeException("Crashium!");
								}
							}, 80);
						}
					} else {
						BadOres.network.sendTo(new PacketRandomTranslation("badores.crashium.nocrash"), (EntityPlayerMP) miner);
					}
				}
			}, 80);
		}
	}

	@Override
	public String getName() {
		return "crashium";
	}

	public static class GenericCrashException extends RuntimeException {

	}
}
