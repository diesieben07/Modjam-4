package mod.badores.ore;

import cpw.mods.fml.relauncher.Side;
import mod.badores.oremanagement.BlockInfo;
import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import java.util.Arrays;
import java.util.List;

/**
 * @author diesieben07
 */
public class Wantarite extends AbstractOre {

	@Override
	public void onHarvest(EntityPlayer miner, World world, int x, int y, int z, Side side) {
		if (side.isServer()) {
            EntityPig pig = new EntityPig(world);
            pig.setPosition(x + 0.5, y + 0.5, z + 0.5);
            pig.setSaddled(true);
            world.spawnEntityInWorld(pig);
        }
	}

    @Override
    public List<ItemStack> getDroppedItems(World world, int x, int y, int z, BlockInfo blockInfo, int meta, int fortune) {
        return Arrays.asList();
    }

    @Override
    public String getName() {
        return "wantarite";
    }

}
