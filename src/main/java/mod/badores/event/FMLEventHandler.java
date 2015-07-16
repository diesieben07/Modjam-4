package mod.badores.event;

import com.google.common.collect.Lists;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import mod.badores.BadOres;
import mod.badores.achievements.BOAchievementList;
import mod.badores.blocks.BlockBadOre;
import mod.badores.items.BadOreItem;
import mod.badores.items.ItemBOIngot;
import mod.badores.items.ItemBlockBadOre;
import mod.badores.ore.BarelyGenerite;
import mod.badores.ore.Fleesonsite;
import mod.badores.ore.Shiftium;
import mod.badores.oremanagement.BadOre;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

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
        if (event.crafting == null) return;

        Item item = event.crafting.getItem();

        if (item instanceof ItemBlockBadOre && ((BlockBadOre) ((ItemBlockBadOre) item).field_150939_a).getOre(event.crafting) instanceof BarelyGenerite) {
            event.player.triggerAchievement(BOAchievementList.buildBarelyGeneriteBlock);
        } else if (item == BadOres.marmiteBread) {
            event.player.triggerAchievement(BOAchievementList.madeMarmiteBread);
        }
    }

    @SubscribeEvent
    public void onItemPickup(PlayerEvent.ItemPickupEvent event) {
        ItemStack stack = event.pickedUp.getEntityItem();
        if (stack.getItem() instanceof ItemBlockBadOre) {
            BadOre ore = ((BlockBadOre) ((ItemBlockBadOre) stack.getItem()).field_150939_a).getOre(stack);
            if (ore instanceof Shiftium) {
                event.player.triggerAchievement(BOAchievementList.getShiftium);
            } else if (ore instanceof BarelyGenerite) {
                event.player.triggerAchievement(BOAchievementList.barelyGeneriteFound);
            }
        }
    }

    @SubscribeEvent
    public void onSmelting(PlayerEvent.ItemSmeltedEvent event) {
        if (ItemBOIngot.getOre(event.smelting) instanceof Fleesonsite) {
            event.player.triggerAchievement(BOAchievementList.smeltedFleesonsite);
        }
    }

    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.modID.equals(BadOres.MOD_ID)) {
            BadOres.config.sync();
        }
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
