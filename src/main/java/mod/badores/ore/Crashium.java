package mod.badores.ore;

import cpw.mods.fml.relauncher.Side;
import mod.badores.BadOres;
import mod.badores.event.TickEvents;
import mod.badores.network.PacketRandomTranslation;
import mod.badores.oremanagement.BadOre;
import mod.badores.util.JavaUtils;
import net.minecraft.crash.CrashReport;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ReportedException;
import net.minecraft.world.World;

/**
 * @author diesieben07
 */
public class Crashium extends AbstractOre {

    private static final int NUM_NO_CRASH_MESSAGES = 5;
	private static final int NUM_PRE_CRASH = 1;
	private static final int NUM_CRASH_MESSAGES = 10;

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
