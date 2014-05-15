package mod.badores.ore;

import cpw.mods.fml.relauncher.Side;
import mod.badores.BadOres;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;

/**
 * @author diesieben07
 */
public class Crashium extends AbstractOre {

    private static final int NUM_MESSAGES = 5;

    @Override
	public void onRemove(EntityPlayer miner, World world, int x, int y, int z, Side side) {
        if (side.isServer())
        {
            if (!BadOres.devEnv && world.rand.nextFloat() < 0.1f)
            {
		        throw new GenericCrashException();
            }
            else
            {
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
