package mod.badores.ore;

import cpw.mods.fml.relauncher.Side;
import mod.badores.oremanagement.OreForm;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * @author diesieben07
 */
public class Idlikeabite extends AbstractOre {

	@Override
    public void onHarvest(EntityPlayer miner, World world, int x, int y, int z, Side side) {
		if (side.isServer())
			miner.getFoodStats().addExhaustion(rand.nextFloat() * 40.0f);
	}

    @Override
    public void onInventoryTick(OreForm form, ItemStack stack, int slot, EntityPlayer player, World world, Side side) {
        if (side.isServer() && rand.nextInt(200) == 0) {
            player.getFoodStats().addExhaustion(1.0f);
        }
    }


    @Override
	public String getName() {
		return "idlikeabite";
	}
}
