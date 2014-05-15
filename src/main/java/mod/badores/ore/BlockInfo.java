package mod.badores.ore;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

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

	public ItemStack asStack() {
		return new ItemStack(block, 1, metadata);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		BlockInfo blockInfo = (BlockInfo) o;

		if (metadata != blockInfo.metadata) return false;
		if (!block.equals(blockInfo.block)) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = block.hashCode();
		result = 31 * result + metadata;
		return result;
	}
}
