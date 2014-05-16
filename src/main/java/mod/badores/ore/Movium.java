package mod.badores.ore;

import com.google.common.collect.ImmutableList;
import cpw.mods.fml.relauncher.Side;
import mod.badores.BadOres;
import mod.badores.oremanagement.BlockInfo;
import mod.badores.util.JavaUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.List;

/**
 * @author diesieben07
 */
public class Movium extends AbstractOre {

	@Override
	public void onRemove(EntityPlayer miner, World world, int x, int y, int z, Side side) {
		if (side.isServer()) {
			ForgeDirection dir = JavaUtils.selectRandom(rand, ForgeDirection.VALID_DIRECTIONS);
			int tX = x + dir.offsetX;
			int tY = y + dir.offsetY;
			int tZ = z + dir.offsetZ;

			BadOres.oreManager.getBlockInfo(this).placeAt(world, tX, tY, tZ);
		}
	}

	@Override
	public List<ItemStack> getDroppedItems(World world, int x, int y, int z, int meta, int fortune) {
		return ImmutableList.of();
	}

	@Override
	public String getName() {
		return "movium";
	}
}
