package mod.badores.ore;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.Arrays;
import java.util.List;

/**
 * @author diesieben07
 */
public class Marmite extends AbstractOre {

    @Override
    public boolean hasIngot() {
        return true;
    }

    @Override
    protected Block replace() {
        return Blocks.clay;
    }

    @Override
	public String getName() {
		return "marmite";
	}
}
