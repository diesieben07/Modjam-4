package mod.badores.ore;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import java.util.List;

/**
 * @author diesieben07
 */
public class Wannafite extends AbstractOre {

	private static final DamageSource wannafiteDamage = new DamageSource("badores.wannafite");

    @Override
    protected void addOreDrops(World world, int x, int y, int z, int meta, int fortune, List<ItemStack> drops) {
        drops.add(new ItemStack(Items.iron_sword, 1, rand.nextInt(100) + 100));
    }

    @Override
	public void onHarvest(EntityPlayer miner, World world, int x, int y, int z, Side side, boolean isIngotBlock) {
		miner.attackEntityFrom(wannafiteDamage, 4);
	}

	@Override
	public String getName() {
		return "wannafite";
	}

}
