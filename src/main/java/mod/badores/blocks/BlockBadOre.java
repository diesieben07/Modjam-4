package mod.badores.blocks;

import gnu.trove.map.TIntObjectMap;
import gnu.trove.map.hash.TIntObjectHashMap;
import mod.badores.ore.BadOre;
import mod.badores.util.Sides;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * @author diesieben07
 */
public class BlockBadOre extends BOBlock {

	private final TIntObjectMap<BadOre> ores = new TIntObjectHashMap<>();

	public BlockBadOre() {
		super(Material.rock);
	}

	public void addOre(int metadata, BadOre ore) {
		ores.put(metadata, ore);
	}

	private BadOre getOre(int metadata) {
		BadOre ore = ores.get(metadata);
		if (ore == null) {
			return ores.get(0);
		} else {
			return ore;
		}
	}

	@Override
	public void onBlockHarvested(World world, int x, int y, int z, int meta, EntityPlayer player) {
		super.onBlockHarvested(world, x, y, z, meta, player);
		getOre(meta).onMined(player, world, x, y, z, Sides.logical(world));
	}

}
