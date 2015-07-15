package mod.badores.ore;

import cpw.mods.fml.relauncher.Side;
import mod.badores.achievements.BOAchievementList;
import mod.badores.oremanagement.OreForm;
import mod.badores.util.FakePlayerDetection;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

/**
 * @author diesieben07
 */
public class Killium extends AbstractOre {

	private static final DamageSource killiumDamage = new DamageSource("badores.killium").setDamageBypassesArmor().setDamageIsAbsolute();

	@Override
	public void onHarvest(EntityPlayer miner, World world, int x, int y, int z, Side side, boolean isIngotBlock) {
		if (FakePlayerDetection.isFakePlayer(miner)) return;
		if (rand.nextInt(5) == 0) {
            kill(miner);
        } else {
            miner.triggerAchievement(BOAchievementList.minedKillium);
        }
	}

	private void kill(EntityPlayer miner) {
		miner.attackEntityFrom(killiumDamage, 10000.0f);
	}

	@Override
	public void onInventoryTick(OreForm form, ItemStack stack, int slot, EntityPlayer player, World world, Side side) {
		if (rand.nextInt(1000) == 0) {
			kill(player);
		}
	}

	@Override
	public String getName() {
		return "killium";
	}

}
