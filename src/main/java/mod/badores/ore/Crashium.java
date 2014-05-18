package mod.badores.ore;

import cpw.mods.fml.relauncher.Side;
import mod.badores.BadOres;
import mod.badores.event.FMLEventHandler;
import mod.badores.network.PacketRandomTranslation;
import mod.badores.oremanagement.ToolInfo;
import mod.badores.oremanagement.ToolType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;

/**
 * @author diesieben07
 */
public class Crashium extends AbstractOre {

	public static final int CRASH_PROBABILITY = 5;

	@Override
	public void onRemove(final EntityPlayer miner, final World world, int x, int y, int z, Side side) {
		doCrash(miner, side);
	}

	@Override
	public void onToolMine(ToolType type, EntityPlayer player, World world, int x, int y, int z, Side side) {
		doCrash(player, side);
	}

	@Override
	public void onToolEntityAttack(ToolType type, EntityPlayer player, EntityLivingBase target, World world, Side side) {
		doCrash(player, side);
	}

	private void doCrash(EntityPlayer miner, Side side) {
		if (side.isServer()) {
			final EntityPlayerMP player = ((EntityPlayerMP) miner);
			BadOres.network.sendTo(new PacketRandomTranslation("badores.crashium.precrash"), player);

			FMLEventHandler.INSTANCE.schedule(new Runnable() {
				@Override
				public void run() {
					if (rand.nextInt(CRASH_PROBABILITY) == 0) {
						BadOres.network.sendTo(new PacketRandomTranslation("badores.crashium.crash"), player);
						if (!BadOres.devEnv && BadOres.gameBreakingFeatures) {
							FMLEventHandler.INSTANCE.schedule(new Runnable() {
								@Override
								public void run() {
									throw new RuntimeException("Crashium!");
								}
							}, 80);
						}
					} else {
						BadOres.network.sendTo(new PacketRandomTranslation("badores.crashium.nocrash"), player);
					}
				}
			}, 80);
		}
	}

	@Override
	public boolean hasIngot() {
		return true;
	}

	@Override
	public boolean hasTools() {
		return true;
	}

	@Override
	public ToolInfo getToolInfo() {
		// TODO: different values?
		return new ToolInfo(2, 250, 6.0F, 2.0F, 14);
	}

	@Override
	public String getName() {
		return "crashium";
	}

	public static class GenericCrashException extends RuntimeException {

	}
}
