package mod.badores.ore;

import cpw.mods.fml.relauncher.Side;
import mod.badores.util.FakePlayerDetection;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * @author diesieben07
 */
public class Breakium extends AbstractOre {

	@Override
	public void onHarvest(EntityPlayer miner, World world, int x, int y, int z, Side side, boolean isIngotBlock) {
		if (FakePlayerDetection.isFakePlayer(miner)) return;
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
