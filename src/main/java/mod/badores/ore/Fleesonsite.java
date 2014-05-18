package mod.badores.ore;

import cpw.mods.fml.relauncher.Side;
import mod.badores.blocks.BlockTickProvider;
import mod.badores.entities.EntityFleeingBlock;
import mod.badores.oremanagement.BlockInfo;
import mod.badores.oremanagement.OreForm;
import mod.badores.util.JavaUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.Random;

/**
 * @author diesieben07
 */
public class Fleesonsite extends AbstractOre {

    @Override
    public boolean canTick() {
        return true;
    }

    @Override
	public int initialTickRate(boolean isIngotBlock) {
		return 40;
	}

	@Override
	public void tick(World world, int x, int y, int z, Random random, Side side, boolean isIngotBlock, BlockTickProvider tickProvider) {
		super.tick(world, x, y, z, random, side, isIngotBlock, tickProvider);

        EntityPlayer player = world.getClosestPlayer(x + 0.5, y + 0.5, z + 0.5, 6.0);
		if (player != null) {
			flee(world, x, y, z, player);
		}
	}

	@Override
	public boolean onRightClick(World world, int x, int y, int z, ForgeDirection clickSide, EntityPlayer player, boolean isIngotBlock, Side side) {
		if (side.isServer()) {
			flee(world, x, y, z, player);
		}
		return true;
	}

	@Override
	public void onLeftClick(World world, int x, int y, int z, EntityPlayer player, boolean isIngotBlock, Side side) {
		if (side.isServer()) {
			flee(world, x, y, z, player);
		}
	}

	private void flee(World world, int x, int y, int z, EntityPlayer player) {
		BlockInfo blockInfo = blockInfo();
		world.setBlockToAir(x, y, z);
		EntityFleeingBlock fleeingBlock = new EntityFleeingBlock(world, blockInfo.block, blockInfo.metadata);
		fleeingBlock.setPosition(x + 0.5, y, z + 0.5);
		fleeingBlock.setRevengeTarget(player);
		fleeingBlock.setTarget(player);
		world.spawnEntityInWorld(fleeingBlock);
		fleeingBlock.playLivingSound();
	}

	@Override
	public void onInventoryTick(OreForm form, ItemStack stack, int slot, EntityPlayer player, World world, Side side) {
		if (side.isServer()) {
			jumpRandomly(player.inventoryContainer, player.inventoryContainer.getSlotFromInventory(player.inventory, slot));
		}
	}

	@Override
	public void onContainerTick(OreForm form, Container container, Slot slot, ItemStack stack) {
		jumpRandomly(container, slot);
	}

	private void jumpRandomly(Container c, Slot slot) {
		ItemStack stack = slot.getStack();
		if (rand.nextInt(150) == 0) {
			Slot targetSlot;
			int count = 0;
			do {
				targetSlot = (Slot) JavaUtils.selectRandom(rand, c.inventorySlots);
				if (++count == 15) {
					return;
				}
			} while (targetSlot == slot || !slot.getHasStack() || !slot.isItemValid(stack));
			slot.putStack(null);
			targetSlot.putStack(stack);
		}
	}

	@Override
	public String getName() {
		return "fleesonsite";
	}
}
