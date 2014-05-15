package mod.badores.ore;

import net.minecraft.block.Block;

/**
 * @author diesieben07
 */
public final class BlockInfo {

	public final Block block;
	public final int metadata;

	public BlockInfo(Block block, int metadata) {
		this.block = block;
		this.metadata = metadata;
	}
}
