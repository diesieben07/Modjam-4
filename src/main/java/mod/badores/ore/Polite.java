package mod.badores.ore;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;

/**
 * @author diesieben07
 */
public class Polite implements BadOre {


	@Override
	public void onMined(EntityPlayer miner, World world, int x, int y, int z, Side side) {
		if (side.isServer()) {
			// TODO: randomize messages
			miner.addChatComponentMessage(new ChatComponentTranslation("badores.polite.1"));
		}
	}

}
