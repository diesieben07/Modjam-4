package mod.badores.ore;

import cpw.mods.fml.relauncher.Side;
import mod.badores.oremanagement.ArmorInfo;
import mod.badores.oremanagement.ArmorType;
import mod.badores.oremanagement.ToolInfo;
import mod.badores.oremanagement.ToolType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

/**
 * @author diesieben07
 */
public class Smite extends AbstractOre {

	@Override
	public void onRemove(EntityPlayer miner, World world, int x, int y, int z, Side side) {
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
        return new ArmorInfo(100, new int[]{1, 3, 2, 1}, 15);
    }

    @Override
    public ToolInfo getToolInfo() {
        return new ToolInfo(0, 100, 2.0F, 1.0F, 15);
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
        world.addWeatherEffect(new EntityLightningBolt(world, entity.posX, world.getPrecipitationHeight(MathHelper.floor_double(entity.posX), MathHelper.floor_double(entity.posY)), entity.posZ));
    }

    @Override
	public String getName() {
		return "smite";
	}
}
