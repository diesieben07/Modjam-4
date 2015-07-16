package mod.badores.ore;

import cpw.mods.fml.relauncher.Side;
import mod.badores.BadOres;
import mod.badores.util.FakePlayerDetection;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.List;

/**
 * @author diesieben07
 */
public class Website extends AbstractOre {

    @Override
    protected void addOreDrops(World world, int x, int y, int z, int meta, int fortune, List<ItemStack> drops) {

    }

    @Override
	public void onRemove(EntityPlayer miner, World world, int x, int y, int z, Side side, boolean isIngotBlock) {
		if (FakePlayerDetection.isFakePlayer(miner)) return;
		if (side.isClient()) {
			BadOres.proxy.openRandomWebsite();
		}
	}

	@Override
	public String getName() {
		return "website";
	}
}
