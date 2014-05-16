package mod.badores.ore;

import com.google.common.base.Throwables;
import cpw.mods.fml.relauncher.Side;
import mod.badores.BadOres;
import mod.badores.oremanagement.OreForm;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.FoodStats;
import net.minecraft.world.World;

import java.lang.reflect.Field;

/**
 * @author diesieben07
 */
public class Idlikeabite extends AbstractOre {

	private static Field foodLevel;

	private static void depleteFood(EntityPlayer player, int amount) {
		try {
			if (foodLevel == null) {
				String fName = BadOres.devEnv ? "foodLevel" : "field_75127_a";
				foodLevel = FoodStats.class.getDeclaredField(fName);
				foodLevel.setAccessible(true);
			}
			int curr = foodLevel.getInt(player.getFoodStats());
			int now = Math.max(0, curr - amount);
			foodLevel.setInt(player.getFoodStats(), now);
		} catch (Exception e) {
			Throwables.propagate(e);
		}
	}

	@Override
	public void onHarvest(EntityPlayer miner, World world, int x, int y, int z, Side side) {
		if (side.isServer()) {
			depleteFood(miner, 6);
		}
	}

	@Override
	public void onInventoryTick(OreForm form, ItemStack stack, int slot, EntityPlayer player, World world, Side side) {
		if (side.isServer() && rand.nextInt(200) == 0) {
			depleteFood(player, 1);
		}
	}

	@Override
	public String getName() {
		return "idlikeabite";
	}
}
