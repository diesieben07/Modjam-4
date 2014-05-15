package mod.badores.ore;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

/**
 * @author diesieben07
 */
public class Smite extends AbstractOre {

	@Override
	public void onRemove(EntityPlayer miner, World world, int x, int y, int z, Side side) {
		if (side.isServer()){
            world.addWeatherEffect(new EntityLightningBolt(world, miner.posX, world.getPrecipitationHeight(MathHelper.floor_double(miner.posX), MathHelper.floor_double(miner.posY)), miner.posZ));
        }
	}

	@Override
	public String getName() {
		return "smite";
	}
}
