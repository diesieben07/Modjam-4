package mod.badores.event;

import com.google.common.collect.Lists;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import mod.badores.BadOres;
import mod.badores.achievements.BOAchievementList;
import mod.badores.blocks.BlockBadOre;
import mod.badores.entities.EntityNosleeptonite;
import mod.badores.items.BadOreItem;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

import java.util.Iterator;
import java.util.List;

/**
 * @author diesieben07
 */
public enum FMLEventHandler {

	INSTANCE;

	private final List<Task> tasks = Lists.newArrayList();
	private final List<Task> scheduledNow = Lists.newArrayList();

	public void schedule(Runnable task, int ticks) {
		tasks.add(new Task(task, ticks));
	}

	@SubscribeEvent
	public void onTick(TickEvent.ServerTickEvent event) {
		if (tasks.size() > 0) {
			List<Task> sn = scheduledNow;
			for (Iterator<Task> it = tasks.iterator(); it.hasNext(); ) {
				Task t = it.next();
				if (t.tick()) {
					sn.add(t);
					it.remove();
				}
			}
			for (Task t : sn) {
				t.r.run();
			}
			sn.clear();
		}
	}

	private static final String NBT_KEY = "badores.firstjoin";

	@SubscribeEvent
	public void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
		NBTTagCompound data = event.player.getEntityData();
		NBTTagCompound persistent;
		if (!data.hasKey(EntityPlayer.PERSISTED_NBT_TAG)) {
			data.setTag(EntityPlayer.PERSISTED_NBT_TAG, (persistent = new NBTTagCompound()));
		} else {
			persistent = data.getCompoundTag(EntityPlayer.PERSISTED_NBT_TAG);
		}

		if (!persistent.hasKey(NBT_KEY)) {
			persistent.setBoolean(NBT_KEY, true);
			event.player.inventory.addItemStackToInventory(new ItemStack(BadOres.badOreBook));
		}
	}

	@SubscribeEvent
	public void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.side.isServer() && event.phase == TickEvent.Phase.START) {
			Container c = event.player.openContainer;
			if (c != event.player.inventoryContainer) {
				@SuppressWarnings("unchecked")
				List<Slot> slots = c.inventorySlots;
				int l = slots.size();
				for (int i = 0; i < l; ++i) { // avoid generating iterator garbage every tick
					Slot slot = slots.get(i);
					if (slot.inventory != event.player.inventory) {
						ItemStack stack = slot.getStack();
						Item item;
						if (stack != null && ((item = stack.getItem()) instanceof BadOreItem)) {
							((BadOreItem) item).onContainerTick(c, slot, stack);
						}
					}
				}
			}
		}
	}

    @SubscribeEvent
    public void onCrafting(PlayerEvent.ItemCraftedEvent event) {
        ItemStack barelyGeneriteBI = BlockBadOre.createIngotBlock(BadOres.oreManager.getOreByName("barelyGenerite"));
        if (barelyGeneriteBI.isItemEqual(event.crafting))
            event.player.triggerAchievement(BOAchievementList.buildBarelyGeneriteBlock);
    }

    @SubscribeEvent
    public void onItemPickup(PlayerEvent.ItemPickupEvent event) {
        ItemStack barelyGeneriteBO = BlockBadOre.createOre(BadOres.oreManager.getOreByName("barelyGenerite"));
        if (barelyGeneriteBO.isItemEqual(event.pickedUp.getEntityItem()))
            event.player.triggerAchievement(BOAchievementList.barelyGeneriteFound);
    }

	private class Task {

		final Runnable r;
		int remaining;

		private Task(Runnable r, int remaining) {
			this.r = r;
			this.remaining = remaining;
		}

		boolean tick() {
			return --remaining == 0;
		}

	}


}
