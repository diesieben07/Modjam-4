package mod.badores.ore;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.List;

/**
 * @author diesieben07
 */
public class Wantarite extends AbstractOre {

	@Override
	public void onHarvest(EntityPlayer miner, World world, int x, int y, int z, Side side, boolean isIngotBlock) {
		if (side.isServer()) {
			EntityPig pig = new EntityPig(world);
			pig.setPosition(x + 0.5, y + 0.5, z + 0.5);
			pig.setSaddled(true);
			world.spawnEntityInWorld(pig);
		}
	}

	@Override
	public void addDroppedItems(World world, int x, int y, int z, int meta, int fortune, List<ItemStack> drops, boolean isIngotBlock) {
		// no drops
	}

	@Override
	public String getName() {
		return "wantarite";
	}

}
