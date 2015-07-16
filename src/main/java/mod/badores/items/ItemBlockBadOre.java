package mod.badores.items;

import mod.badores.blocks.BlockBadOre;
import mod.badores.ore.AbstractOre;
import mod.badores.oremanagement.OreSubName;
import mod.badores.util.I18n;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * @author diesieben07
 */
public class ItemBlockBadOre extends ItemBlock implements BadOreItem {

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
        if (BlockBadOre.isIngotBlock(stack)) {
            return ((BlockBadOre) field_150939_a).getOre(stack).getDisplayName(INGOT_BLOCK_NAME);
        } else {
            return ((BlockBadOre) field_150939_a).getOre(stack.getItemDamage()).getDisplayName();
        }
    }

    @Override
    public void onUpdate(ItemStack stack, World world, Entity player, int slot, boolean inHotbar) {
        super.onUpdate(stack, world, player, slot, inHotbar);
        AbstractOre.invokeInventoryTick(((BlockBadOre) field_150939_a).getOre(stack), BlockBadOre.getOreForm(stack), stack, slot, world, player);
    }

    @Override
    public void onContainerTick(Container c, Slot slot, ItemStack stack) {
        ((BlockBadOre) field_150939_a).getOre(stack).onContainerTick(BlockBadOre.getOreForm(stack), c, slot, stack);
    }

    private static final OreSubName INGOT_BLOCK_NAME = new OreSubName() {
        @Override
        public String subName(String translatedOreName) {
            return I18n.translateBO("ingotBlock", translatedOreName);
        }
    };
}
