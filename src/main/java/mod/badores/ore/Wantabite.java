package mod.badores.ore;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * @author diesieben07
 */
public class Wantabite extends AbstractOre {

	@Override
	public void onRemove(EntityPlayer miner, World world, int x, int y, int z, Side side) {
		if (side.isServer())
            miner.getFoodStats().addExhaustion(rand.nextFloat() * 40.0f);
	}

	@Override
	public String getName() {
		return "wantabite";
	}
}
