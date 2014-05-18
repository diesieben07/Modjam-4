package mod.badores.ore;

import cpw.mods.fml.relauncher.Side;
import mod.badores.BadOres;
import mod.badores.util.JavaUtils;
import net.minecraft.block.Block;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author diesieben07
 */
public class Pandaemonium extends AbstractOre {

	public static final List<Item> items = Arrays.asList(Item.getItemFromBlock(Blocks.netherrack), Item.getItemFromBlock(Blocks.nether_wart), Item.getItemFromBlock(Blocks.obsidian), Items.fire_charge, Items.blaze_rod, Items.magma_cream);

	public static void setBlockSafe(World world, int x, int y, int z, Block block, int meta, int notifyBits)
    {
        if (world.getBlock(x, y, z).getBlockHardness(world, x, y, z) >= 0f)
            world.setBlock(x, y, z, block, meta, notifyBits);
    }

    @Override
    public int initialTickRate() {
        return 1000;
    }

    @Override
    public void tick(World world, int x, int y, int z, Random random, Side side) {
        if (side.isServer() && rand.nextInt(10) == 0)
            world.playSoundEffect(x + 0.5, y + 0.5, z + 0.5, BadOres.MOD_ID + ":pandaemonium.mine", 1.0f, 1.0f);
    }

    @Override
    public void onHarvest(EntityPlayer miner, World world, int x, int y, int z, Side side) {
        if (side.isServer()) {
            world.playSoundEffect(x + 0.5, y + 0.5, z + 0.5, BadOres.MOD_ID + ":pandaemonium.mine", 1.0f, 1.0f);

            int pigmen = rand.nextInt(4);
            for (int i = 0; i < pigmen; i++) {
                EntityPigZombie entityPigZombie = new EntityPigZombie(world);
                entityPigZombie.setPosition(x + 0.5, y, z + 0.5);
                world.spawnEntityInWorld(entityPigZombie);
            }

            int veins = rand.nextInt(12) + 3;
            for (int i = 0; i < veins; i++) {
                float xP = rand.nextFloat() - rand.nextFloat();
                float yP = rand.nextFloat() - rand.nextFloat();
                float zP = rand.nextFloat() - rand.nextFloat();
                int length = rand.nextInt(10) + 3;

                for (int iteration = 0; iteration < length; iteration++) {
                    float curX = x + 0.5f + xP * iteration;
                    float curY = y + 0.5f + yP * iteration;
                    float curZ = z + 0.5f + zP * iteration;

                    setBlockSafe(world, MathHelper.floor_float(curX), MathHelper.floor_float(curY), MathHelper.floor_float(curZ), Blocks.netherrack, 0, 3);
                }
            }

            int fireRange = rand.nextInt(8);
            float fireChance = rand.nextFloat() * rand.nextFloat();
            for (int xP = -fireRange; xP <= fireRange; xP++)
                for (int yP = -fireRange; yP <= fireRange; yP++)
                    for (int zP = -fireRange; zP <= fireRange; zP++)
                    {
                        if (rand.nextFloat() < fireChance)
                        {
                            if (Blocks.fire.canPlaceBlockAt(world, x + xP, y + yP, z + zP))
                                setBlockSafe(world, x + xP, y + yP, z + zP, Blocks.fire, 0, 3);
                        }
                    }
        }
    }

    @Override
    public void getDroppedItems(World world, int x, int y, int z, int meta, int fortune, List<ItemStack> drops) {
	    int num = rand.nextInt(3);
        for (int i = 0; i < num; i++) {
            Item item = JavaUtils.selectRandom(rand, items);
            drops.add(new ItemStack(item, rand.nextInt(5)));
        }
    }

	@Override
    protected int genMin(Random random, World world, int chunkX, int chunkZ) {
        return 0;
    }

    @Override
    protected int genMax(Random random, World world, int chunkX, int chunkZ) {
        return 10;
    }

    @Override
	public String getName() {
		return "pandaemonium";
	}
}
