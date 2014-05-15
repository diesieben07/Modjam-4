package mod.badores.items;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

/**
 * @author diesieben07
 */
public class ItemBlockMetadata extends ItemBlock {

	public ItemBlockMetadata(Block block) {
		super(block);
	}

	@Override
	public int getMetadata(int itemMeta) {
		return itemMeta;
	}
}
