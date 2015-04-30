package mod.badores.ore;

import cpw.mods.fml.relauncher.Side;
import mod.badores.blocks.BlockTickProvider;
import mod.badores.oremanagement.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.Random;

/**
 * @author diesieben07
 */
public class Amadeum extends AbstractOre {

	@Override
	protected int veinsPerChunk(Random r, World w, int chunkX, int chunkZ) {
		return r.nextInt(100) == 0 ? 1 : 0;
	}

    @Override
    public boolean canTick(boolean isIngotBlock) {
        return true;
    }

    @Override
	public int initialTickRate(boolean isIngotBlock) {
		return rand.nextInt(6) + 18;
	}

	private static final String[] soundNames = {
			"harp", "bd", "snare", "hat", "bassattack"
	};

	@Override
	public void tick(World world, int x, int y, int z, Random random, Side side, boolean isIngotBlock, BlockTickProvider tickProvider) {
		super.tick(world, x, y, z, random, side, isIngotBlock, tickProvider);

		if (side.isServer()) {
			randomSound(world, x, y, z, random);
		}
	}

	private void randomSound(World world, double x, double y, double z, Random random) {
		int soundId = random.nextInt(5);
		int note = random.nextInt(25);

		float f = (float) Math.pow(2.0D, (note - 12) / 12.0D);
		String soundName = soundNames[soundId];

		world.playSoundEffect(x + 0.5D, y + 0.5D, z + 0.5D, "note." + soundName, 3.0F, f);
//            world.spawnParticle("note", (double)x + 0.5D, (double)y + 1.2D, (double)z + 0.5D, (double)note / 24.0D, 0.0D, 0.0D);
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
	public boolean hasIngot() {
		return true;
	}

	@Override
	public ArmorInfo getArmorInfo() {
		return new ArmorInfo(10, new int[]{2, 6, 5, 2}, 20);
	}

	@Override
	public ToolInfo getToolInfo() {
		return new ToolInfo(2, 250, 6.0F, 2.0F, 20);
	}

	@Override
	public String getName() {
		return "amadeum";
	}

	@Override
	public void onArmorTick(ArmorType type, EntityPlayer player, World world, Side side, int slot, ItemStack stack) {
		playSoundFromItem(player, world);
	}

	@Override
	public void onInventoryTick(OreForm form, ItemStack stack, int slot, EntityPlayer player, World world, Side side) {
		playSoundFromItem(player, world);
	}

	private void playSoundFromItem(EntityPlayer player, World world) {
		if (rand.nextInt(200) == 0) {
			randomSound(world, player.posX, player.posY, player.posZ, rand);
		}
	}

}
