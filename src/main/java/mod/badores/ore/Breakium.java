package mod.badores.ore;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * @author diesieben07
 */
public class Breakium extends AbstractOre {

	@Override
	public void onHarvest(EntityPlayer miner, World world, int x, int y, int z, Side side) {
		ItemStack equip = miner.getCurrentEquippedItem();
		if (equip != null) {
			miner.renderBrokenItemStack(equip);
			miner.destroyCurrentEquippedItem();
		}
	}

	@Override
	public String getName() {
		return "breakium";
	}
}
