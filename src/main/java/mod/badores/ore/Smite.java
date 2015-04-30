package mod.badores.ore;

import cpw.mods.fml.relauncher.Side;
import mod.badores.items.ItemBOIngot;
import mod.badores.oremanagement.*;
import mod.badores.util.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

/**
 * @author diesieben07
 */
public class Smite extends AbstractOre {

	@Override
	public void onRemove(EntityPlayer miner, World world, int x, int y, int z, Side side, boolean isIngotBlock) {
		if (side.isServer()) {
            if (rand.nextInt(3) == 0)
                spawnLightning(world, miner);
		}
	}

    @Override
    public boolean hasIngot() {
        return true;
    }

    @Override
    public boolean hasArmor() {
        return true;
    }

    @Override
    public boolean hasTools() {
        return true;
    }

    @Override
    public ArmorInfo getArmorInfo() {
        return new ArmorInfo(8, new int[]{2, 5, 4, 2}, 8);
    }

    @Override
    public ToolInfo getToolInfo() {
        return new ToolInfo(2, 220, 5.0F, 2.0F, 8);
    }

    @Override
    public String getDisplayName(OreSubName name) {
        if (name instanceof ItemBOIngot)
            return I18n.translate(getName() + ".ingot.name");
        else
            return super.getDisplayName(name);
    }

    @Override
    public void onArmorTick(ArmorType type, EntityPlayer player, World world, Side side, int slot, ItemStack stack) {
        if (side.isServer() && rand.nextInt(200) == 0) {
            spawnLightning(world, player);
        }
    }

    @Override
    public void onToolEntityAttack(ToolType type, EntityPlayer player, EntityLivingBase target, World world, Side side) {
        if (side.isServer() && rand.nextInt(2) == 0) {
            spawnLightning(world, rand.nextBoolean() ? player : target);
        }
    }

    private void spawnLightning(World world, Entity entity)
    {
        world.addWeatherEffect(new EntityLightningBolt(world, entity.posX, world.getPrecipitationHeight(MathHelper.floor_double(entity.posX), MathHelper.floor_double(entity.posZ)), entity.posZ));
    }

    @Override
	public String getName() {
		return "smite";
	}
}
