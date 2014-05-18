package mod.badores.ore;

import cpw.mods.fml.relauncher.Side;
import mod.badores.entities.EntityFleeingBlock;
import mod.badores.oremanagement.BlockInfo;
import mod.badores.oremanagement.OreForm;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.Random;

/**
 * @author diesieben07
 */
public class Fleesonsite extends AbstractOre {

	@Override
	public int initialTickRate(boolean isIngotBlock) {
		return 40;
	}

	@Override
	public void tick(World world, int x, int y, int z, Random random, Side side, boolean isIngotBlock) {
		super.tick(world, x, y, z, random, side, isIngotBlock);

        EntityPlayer player = world.getClosestPlayer(x + 0.5, y + 0.5, z + 0.5, 6.0);
		if (player != null) {
            BlockInfo blockInfo = blockInfo();
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
		if (side.isServer()) {
			jumpRandomly(player.inventory, slot, stack);
		}
	}

	@Override
	public void onContainerTick(OreForm form, Container container, Slot slot, ItemStack stack) {
		jumpRandomly(slot.inventory, slot.getSlotIndex(), stack);
	}

	private void jumpRandomly(IInventory inv, int slot, ItemStack stack) {
		if (rand.nextInt(150) == 0) {
			int targetSlot;
			int count = 0;
			do {
				targetSlot = rand.nextInt(inv.getSizeInventory());
				if (++count == 15) {
					return;
				}
			} while (targetSlot == slot || inv.getStackInSlot(targetSlot) != null || !inv.isItemValidForSlot(targetSlot, stack));
			inv.setInventorySlotContents(targetSlot, stack);
			inv.setInventorySlotContents(slot, null);
			inv.markDirty();
		}
	}

	@Override
	public String getName() {
		return "fleesonsite";
	}
}
