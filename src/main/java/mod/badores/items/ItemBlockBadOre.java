package mod.badores.items;

import mod.badores.blocks.BlockBadOre;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

/**
 * @author diesieben07
 */
public class ItemBlockBadOre extends ItemBlock {

	public ItemBlockBadOre(Block block) {
		super(block);
        setHasSubtypes(true);
	}

	@Override
	public int getMetadata(int itemMeta) {
		return itemMeta;
	}

	@Override
	public String getItemStackDisplayName(ItemStack stack) {
		return StatCollector.translateToLocal("badores." + ((BlockBadOre) field_150939_a).getOre(stack.getItemDamage()).getName());
	}

	@Override
    public String getUnlocalizedName(ItemStack stack) {
        return "badores." + ((BlockBadOre) field_150939_a).getOre(stack.getItemDamage()).getName();
    }
}
