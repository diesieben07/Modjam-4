package mod.badores.ore;

import cpw.mods.fml.relauncher.Side;
import mod.badores.BadOres;
import mod.badores.event.FMLEventHandler;
import mod.badores.items.ItemBOIngot;
import mod.badores.network.PacketRandomTranslation;
import mod.badores.oremanagement.*;
import mod.badores.util.FakePlayerDetection;
import mod.badores.util.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

/**
 * @author diesieben07
 */
public class Crashium extends AbstractOre {

    public static final int CRASH_PROBABILITY = 5;

    @Override
    public void onRemove(final EntityPlayer miner, final World world, int x, int y, int z, Side side, boolean isIngotBlock) {
        doCrash(miner, side);
    }

    @Override
    public void onToolMine(ToolType type, EntityPlayer player, World world, int x, int y, int z, Side side) {
        doCrash(player, side);
    }

    @Override
    public void onToolEntityAttack(ToolType type, EntityPlayer player, EntityLivingBase target, World world, Side side) {
        doCrash(player, side);
    }

    private void doCrash(EntityPlayer miner, Side side) {
        if (FakePlayerDetection.isFakePlayer(miner)) return;
        if (side.isServer()) {
            final EntityPlayerMP player = ((EntityPlayerMP) miner);
            BadOres.network.sendTo(new PacketRandomTranslation("badores.crashium.precrash"), player);

            FMLEventHandler.INSTANCE.schedule(new Runnable() {
                @Override
                public void run() {
                    if (rand.nextInt(CRASH_PROBABILITY) == 0) {
                        BadOres.network.sendTo(new PacketRandomTranslation("badores.crashium.crash"), player);
                        if (!BadOres.devEnv) {
                            FMLEventHandler.INSTANCE.schedule(new Runnable() {
                                @Override
                                public void run() {
                                    throw new RuntimeException("Crashium!");
                                }
                            }, 80);
                        }
                    } else {
                        BadOres.network.sendTo(new PacketRandomTranslation("badores.crashium.nocrash"), player);
                    }
                }
            }, 80);
        }
    }

    @Override
    public boolean hasIngot() {
        return true;
    }

    @Override
    public boolean hasTools() {
        return true;
    }

    @Override
    public ToolInfo getToolInfo() {
        return new ToolInfo(2, 250, 8.0F, 2.0F, 8);
    }

    @Override
    public boolean hasArmor() {
        return true;
    }

    @Override
    public ArmorInfo getArmorInfo() {
        return new ArmorInfo(8, new int[]{2, 7, 5, 2}, 9);
    }

    @Override
    public String getDisplayName(OreSubName name) {
        if (name instanceof ItemBOIngot)
            return I18n.translate(getName() + ".ingot.name");
        else
            return super.getDisplayName(name);
    }

    @Override
    public void onArmorAttacked(ArmorType type, EntityPlayer player, DamageSource damageSource, float amount, World world, Side side) {
        doCrash(player, side);
    }

    @Override
    public void onArmorTick(ArmorType type, EntityPlayer player, World world, Side side, int slot, ItemStack stack) {
        if (rand.nextInt(800) == 0)
            doCrash(player, side);
    }

    @Override
    public String getName() {
        return "crashium";
    }

    public static class GenericCrashException extends RuntimeException {

    }
}
