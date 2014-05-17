package mod.badores.ore;

import cpw.mods.fml.relauncher.Side;
import mod.badores.oremanagement.ArmorInfo;
import mod.badores.oremanagement.ArmorType;
import mod.badores.oremanagement.ToolInfo;
import mod.badores.oremanagement.ToolType;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

/**
 * @author diesieben07
 */
public class Enderite extends AbstractOre {

    private static final int RADIUS = 40;
    private static final int RAD_DOUBLE = RADIUS * 2;

    @Override
    public boolean hasTools() {
        return true;
    }

    @Override
    public ToolInfo getToolInfo() {
        return new ToolInfo(0, 1, 2.0F, 0.0F, 15);
    }

    @Override
    public boolean hasArmor() {
        return true;
    }

    @Override
    public ArmorInfo getArmorInfo() {
        return new ArmorInfo(17, new int[]{2, 7, 5, 2}, 20);
    }

    @Override
    public boolean hasIngot() {
        return true;
    }

    @Override
	public void onHarvest(EntityPlayer miner, World world, int x, int y, int z, Side side) {
		if (side.isServer()) {
            teleportTo(world, miner, findRandomSpot(world, x, y, z));
		}
	}

    @Override
    public void onToolMine(ToolType type, EntityPlayer player, World world, int x, int y, int z, Block block, Side side) {
        if (side.isServer() && rand.nextInt(5) == 0) {
            teleportTo(world, player, findRandomSpot(world, MathHelper.floor_double(player.posX), MathHelper.floor_double(player.posY), MathHelper.floor_double(player.posZ)));
        }
    }

    @Override
    public void onArmorTick(ArmorType type, EntityPlayer player, World world, Side side, int slot, ItemStack stack) {
        if (side.isServer() && rand.nextInt(1000) == 0) {
            teleportTo(world, player, findRandomSpot(world, MathHelper.floor_double(player.posX), MathHelper.floor_double(player.posY), MathHelper.floor_double(player.posZ)));
        }
    }

    @Override
    public void onToolEntityAttack(ToolType type, EntityPlayer player, EntityLivingBase target, World world, Side side) {
        if (side.isServer() && rand.nextInt(5) == 0) {
            teleportTo(world, rand.nextBoolean() ? player : target, findRandomSpot(world, MathHelper.floor_double(player.posX), MathHelper.floor_double(player.posY), MathHelper.floor_double(player.posZ)));
        }
    }

    private ChunkCoordinates findRandomSpot(World world, int x, int y, int z) {
		int rX = x - RADIUS + rand.nextInt(RAD_DOUBLE);
		int rZ = z - RADIUS + rand.nextInt(RAD_DOUBLE);
		int rY = 10 + rand.nextInt(240);
		return new ChunkCoordinates(rX, rY, rZ);
	}

    private void teleportTo(World world, EntityLivingBase entity, ChunkCoordinates coords)
    {
        short short1 = 128;

        for (int l = 0; l < short1; ++l)
        {
            double d6 = (double)l / ((double)short1 - 1.0D);
            float f = (this.rand.nextFloat() - 0.5F) * 0.2F;
            float f1 = (this.rand.nextFloat() - 0.5F) * 0.2F;
            float f2 = (this.rand.nextFloat() - 0.5F) * 0.2F;
            double d7 = coords.posX + (entity.posX - coords.posX) * d6 + (this.rand.nextDouble() - 0.5D) * (double)entity.width * 2.0D;
            double d8 = coords.posY + (entity.posY - coords.posY) * d6 + this.rand.nextDouble() * (double)entity.height;
            double d9 = coords.posZ + (entity.posZ - coords.posZ) * d6 + (this.rand.nextDouble() - 0.5D) * (double)entity.width * 2.0D;
            world.spawnParticle("portal", d7, d8, d9, (double)f, (double)f1, (double)f2);
        }

        world.playSoundEffect(coords.posX, coords.posY, coords.posZ, "mob.endermen.portal", 1.0F, 1.0F);
        entity.playSound("mob.endermen.portal", 1.0F, 1.0F);
        entity.setPositionAndUpdate(coords.posX, coords.posY, coords.posZ);
    }

	@Override
	public String getName() {
		return "enderite";
	}
}
