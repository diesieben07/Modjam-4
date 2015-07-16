package mod.badores.ore;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import mod.badores.achievements.BOAchievementList;
import mod.badores.blocks.BlockBadOre;
import mod.badores.items.ItemBlockBadOre;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

/**
 * @author diesieben07
 */
public class Iwontfite extends AbstractOre {

    public Iwontfite() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onPlayerAttack(LivingHurtEvent event) {
        Entity entity = event.source.getEntity();
        if (!(entity instanceof EntityPlayer)) {
            return;
        }

        IInventory inv = ((EntityPlayer) entity).inventory;
        int len = inv.getSizeInventory();
        for (int i = 0; i < len; ++i) {
            ItemStack stack = inv.getStackInSlot(i);
            if (stack != null && stack.getItem() instanceof ItemBlockBadOre && ((BlockBadOre) ((ItemBlock) stack.getItem()).field_150939_a).getOre(stack) == this) {
                event.ammount = rand.nextInt(1000) == 0 ? 1 : 0;
                if (event.ammount != 0) {
                    ((EntityPlayer) entity).triggerAchievement(BOAchievementList.iwontfiteDamage);
                }
                break;
            }
        }
    }

    @Override
    public String getName() {
        return "iwontfite";
    }
}
