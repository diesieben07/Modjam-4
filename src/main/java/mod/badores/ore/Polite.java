package mod.badores.ore;

import cpw.mods.fml.relauncher.Side;
import mod.badores.BadOres;
import mod.badores.network.PacketRandomTranslation;
import mod.badores.oremanagement.ArmorInfo;
import mod.badores.oremanagement.ArmorType;
import mod.badores.oremanagement.ToolInfo;
import mod.badores.oremanagement.ToolType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

/**
 * @author diesieben07
 */
public class Polite extends AbstractOre {

	@Override
	public void onHarvest(EntityPlayer miner, World world, int x, int y, int z, Side side) {
		if (side.isServer()) {
			BadOres.network.sendTo(new PacketRandomTranslation("badores.polite.mined"), (EntityPlayerMP) miner);
		}
	}

    @Override
    public void onToolMine(ToolType type, EntityPlayer player, World world, int x, int y, int z, Side side) {
        if (side.isServer()) {
            BadOres.network.sendTo(new PacketRandomTranslation("badores.polite.tool"), (EntityPlayerMP) player);
        }
    }

    @Override
    public void onToolEntityAttack(ToolType type, EntityPlayer player, EntityLivingBase target, World world, Side side) {
        if (side.isServer()) {
            BadOres.network.sendTo(new PacketRandomTranslation("badores.polite.attack"), (EntityPlayerMP) player);
        }
    }

    @Override
    public void onArmorAttacked(ArmorType type, EntityPlayer player, DamageSource damageSource, float amount, World world, Side side) {
        if (side.isServer()) {
            BadOres.network.sendTo(new PacketRandomTranslation("badores.polite.defend"), (EntityPlayerMP) player);
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
    public boolean hasArmor() {
        return true;
    }

    @Override
    public ToolInfo getToolInfo() {
        // TODO: different values?
        return new ToolInfo(2, 250, 6.0F, 2.0F, 14);
    }

    @Override
    public ArmorInfo getArmorInfo() {
        return new ArmorInfo(8, new int[]{2, 4, 3, 1}, 8);
    }

    @Override
	public String getName() {
		return "polite";
	}
}
