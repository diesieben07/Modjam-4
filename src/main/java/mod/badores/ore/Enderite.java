package mod.badores.ore;

import cpw.mods.fml.relauncher.Side;
import mod.badores.oremanagement.ArmorInfo;
import mod.badores.oremanagement.ArmorType;
import mod.badores.oremanagement.ToolInfo;
import mod.badores.oremanagement.ToolType;
import net.minecraft.block.Block;
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
        return new ArmorInfo(100, new int[]{1, 3, 2, 1}, 15);
    }

    @Override
    public boolean hasIngot() {
        return true;
    }

    @Override
	public void onHarvest(EntityPlayer miner, World world, int x, int y, int z, Side side) {
		if (side.isServer()) {
			ChunkCoordinates coords = findRandomSpot(world, x, y, z);
			miner.setPositionAndUpdate(coords.posX, coords.posY, coords.posZ);
		}
	}

    @Override
    public void onToolMine(ToolType type, EntityPlayer player, World world, int x, int y, int z, Block block, Side side) {
        if (side.isServer() && rand.nextInt(5) == 0) {
            ChunkCoordinates coords = findRandomSpot(world, MathHelper.floor_double(player.posX), MathHelper.floor_double(player.posY), MathHelper.floor_double(player.posZ));
            player.setPositionAndUpdate(coords.posX, coords.posY, coords.posZ);
        }
    }

    @Override
    public void onArmorTick(ArmorType type, EntityPlayer player, World world, Side side, int slot, ItemStack stack) {
        if (side.isServer() && rand.nextInt(1000) == 0) {
            ChunkCoordinates coords = findRandomSpot(world, MathHelper.floor_double(player.posX), MathHelper.floor_double(player.posY), MathHelper.floor_double(player.posZ));
            player.setPositionAndUpdate(coords.posX, coords.posY, coords.posZ);
        }
    }

    @Override
    public void onToolEntityAttack(ToolType type, EntityPlayer player, EntityLivingBase target, World world, Side side) {
        if (side.isServer() && rand.nextInt(5) == 0) {
            ChunkCoordinates coords = findRandomSpot(world, MathHelper.floor_double(player.posX), MathHelper.floor_double(player.posY), MathHelper.floor_double(player.posZ));
            (rand.nextBoolean() ? player : target).setPositionAndUpdate(coords.posX, coords.posY, coords.posZ);
        }
    }

    private ChunkCoordinates findRandomSpot(World world, int x, int y, int z) {
		int rX = x - RADIUS + rand.nextInt(RAD_DOUBLE);
		int rZ = z - RADIUS + rand.nextInt(RAD_DOUBLE);
		int rY = 10 + rand.nextInt(240);
		return new ChunkCoordinates(rX, rY, rZ);
	}

	@Override
	public String getName() {
		return "enderite";
	}
}
