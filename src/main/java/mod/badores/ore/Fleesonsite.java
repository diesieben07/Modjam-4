package mod.badores.ore;

import cpw.mods.fml.relauncher.Side;
import mod.badores.entities.EntityFleeingBlock;
import mod.badores.oremanagement.BlockInfo;
import mod.badores.oremanagement.OreForm;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.Random;

/**
 * @author diesieben07
 */
public class Fleesonsite extends AbstractOre {

	@Override
	public int initialTickRate() {
		return 40;
	}

	@Override
	public void tick(World world, int x, int y, int z, BlockInfo blockInfo, Random random, Side side) {
		super.tick(world, x, y, z, blockInfo, random, side);

        EntityPlayer player = world.getClosestPlayer(x + 0.5, y + 0.5, z + 0.5, 6.0);
		if (player != null) {
			world.setBlockToAir(x, y, z);
			EntityFleeingBlock fleeingBlock = new EntityFleeingBlock(world, blockInfo.block, blockInfo.metadata);
			fleeingBlock.setPosition(x + 0.5, y, z + 0.5);
            fleeingBlock.setRevengeTarget(player);
            fleeingBlock.setTarget(player);
            world.spawnEntityInWorld(fleeingBlock);
			fleeingBlock.playLivingSound();
		}
	}

	@Override
	public void onInventoryTick(OreForm form, ItemStack stack, int slot, EntityPlayer player, World world, Side side) {
		if (side.isServer() && rand.nextInt(150) == 0) {
			int targetSlot;
			int count = 0;
			do {
				targetSlot = rand.nextInt(player.inventory.mainInventory.length);
				if (++count == 15) {
					return;
				}
			} while (player.inventory.getStackInSlot(targetSlot) != null);
			player.inventory.setInventorySlotContents(targetSlot, stack);
			player.inventory.setInventorySlotContents(slot, null);
		}
	}

	@Override
	public String getName() {
		return "fleesonsite";
	}
}
