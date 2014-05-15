package mod.badores.ore;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;

/**
 * @author diesieben07
 */
public class Polite extends AbstractOre {

    private static final int NUM_MESSAGES = 3;

	@Override
	public void onMined(EntityPlayer miner, World world, int x, int y, int z, Side side) {
		if (side.isServer()) {
			int msg = rand.nextInt(NUM_MESSAGES);
			miner.addChatComponentMessage(new ChatComponentTranslation("badores.polite." + msg));
		}
	}

    @Override
    public String getName() {
        return "polite";
    }
}
