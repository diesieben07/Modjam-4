package mod.badores.ore;

import cpw.mods.fml.relauncher.Side;
import mod.badores.BadOres;
import mod.badores.util.JavaUtils;
import net.minecraft.crash.CrashReport;
import net.minecraft.entity.player.EntityPlayer;
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
	public void onRemove(EntityPlayer miner, World world, int x, int y, int z, Side side) {
        if (side.isServer()) {


            if (BadOres.gameBreakingFeatures && world.rand.nextInt(10) == 0) {
	            String msg = JavaUtils.selectRandom(rand, crashMsg);
	            CrashReport cr = new CrashReport(msg, new RuntimeException());
	            throw new ReportedException(cr);
            } else {
                int msg = rand.nextInt(NUM_NO_CRASH_MESSAGES);
                miner.addChatComponentMessage(new ChatComponentTranslation("badores.crashium.nocrash." + msg));
            }
        }
	}

	@Override
	public String getName() {
		return "crashium";
	}

    public static class GenericCrashException extends RuntimeException {

    }
}
