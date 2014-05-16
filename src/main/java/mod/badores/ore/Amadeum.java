package mod.badores.ore;

import cpw.mods.fml.relauncher.Side;
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
	public int initialTickRate() {
		return rand.nextInt(6) + 18;
	}

	private static final String[] soundNames = {
			"harp", "bd", "snare", "hat", "bassattack"
	};

	@Override
	public void tick(World world, int x, int y, int z, BlockInfo blockInfo, Random random, Side side) {
		super.tick(world, x, y, z, blockInfo, random, side);

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
		// TODO ?
		return new ArmorInfo(100, new int[]{1, 3, 2, 1}, 15);
	}

	@Override
	public ToolInfo getToolInfo() {
		// TODO ?
		return new ToolInfo(0, 1, 2.0F, 0.0F, 15);
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
