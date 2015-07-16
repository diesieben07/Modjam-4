package mod.badores.oremanagement;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

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

    public void placeAt(World world, int x, int y, int z) {
        world.setBlock(x, y, z, block, metadata, 3);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BlockInfo)) return false;

        BlockInfo that = (BlockInfo) o;
        return this.block == that.block && this.metadata == that.metadata;
    }

    @Override
    public int hashCode() {
        int result = System.identityHashCode(block);
        result = 31 * result + metadata;
        return result;
    }
}
