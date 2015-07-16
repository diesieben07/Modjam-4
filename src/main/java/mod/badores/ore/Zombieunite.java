package mod.badores.ore;

import cpw.mods.fml.relauncher.Side;
import mod.badores.achievements.BOAchievementList;
import mod.badores.util.FakePlayerDetection;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

import java.util.List;

/**
 * @author diesieben07
 */
public class Zombieunite extends AbstractOre {

    @Override
    protected void addOreDrops(World world, int x, int y, int z, int meta, int fortune, List<ItemStack> drops) {
        drops.add(new ItemStack(Items.skull, 1, 2));
    }

    @Override
    public float getHardness(World world, int x, int y, int z, boolean isIngotBlock) {
        int numZombies = world.getEntitiesWithinAABB(EntityZombie.class, AxisAlignedBB.getBoundingBox(x - 10, y - 10, z - 10, x + 10, y + 10, z + 10)).size();
        return numZombies < 10 ? -18000000.0F : 3f;
    }

    @Override
    public void onHarvest(EntityPlayer miner, World world, int x, int y, int z, Side side, boolean isIngotBlock) {
        if (FakePlayerDetection.isFakePlayer(miner)) return;
        miner.triggerAchievement(BOAchievementList.minedZombieunite);
    }

    @Override
    public String getName() {
        return "zombieunite";
    }
}
