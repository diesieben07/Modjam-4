package mod.badores.ore;

import com.google.common.collect.ImmutableList;
import cpw.mods.fml.relauncher.Side;
import mod.badores.BadOres;
import mod.badores.util.JavaUtils;
import net.minecraft.crash.CrashReport;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ReportedException;
import net.minecraft.world.World;

import java.util.List;

/**
 * @author diesieben07
 */
public class Crashium extends AbstractOre {

	private static final List<String> crashMsg = ImmutableList.of(
		"Encrypting runtime devastation",
		"Initializing pickaxe control",
		"Shutting down Minecart Motor",
		"Setting up Texture paintcans",
		"Acquiring world dominance",
		"Downloading corruption",
		"Enderman on the loose!",
		"Calculating PI",
		"Black hole too big",
		"Hiding Herobrine",
		"Swapping out Flux Capacitor"
	);

    private static final int NUM_MESSAGES = 5;

    @Override
	public void onRemove(EntityPlayer miner, World world, int x, int y, int z, Side side) {
        if (side.isServer()) {
            if (!BadOres.devEnv && BadOres.gameBreakingFeatures && world.rand.nextInt(10) == 0) {
	            String msg = JavaUtils.selectRandom(rand, crashMsg);
	            CrashReport cr = new CrashReport(msg, new RuntimeException());
	            throw new ReportedException(cr);
            } else {
                int msg = rand.nextInt(NUM_MESSAGES);
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
