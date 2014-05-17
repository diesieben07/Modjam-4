package mod.badores.ore;

import cpw.mods.fml.relauncher.Side;
import mod.badores.BadOres;
import mod.badores.items.ItemBOIngot;
import mod.badores.oremanagement.BlockInfo;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author diesieben07
 */
public class Pandaemonium extends AbstractOre {

    @Override
    public int initialTickRate() {
        return 1000;
    }

    @Override
    public void tick(World world, int x, int y, int z, BlockInfo blockInfo, Random random, Side side) {
        if (side.isServer() && rand.nextInt(10) == 0)
            world.playSound(x + 0.5, y + 0.5, z + 0.5, BadOres.MOD_ID + ":pandaemonium.mine", 1.0f, 1.0f, false);
    }

    @Override
    public void onHarvest(EntityPlayer miner, World world, int x, int y, int z, Side side) {
        if (side.isServer()) {
            world.playSound(x + 0.5, y + 0.5, z + 0.5, BadOres.MOD_ID + ":pandaemonium.mine", 1.0f, 1.0f, false);

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

                    world.setBlock(MathHelper.floor_float(curX), MathHelper.floor_float(curY), MathHelper.floor_float(curZ), Blocks.netherrack);
                }
            }
        }
    }

    @Override
    public List<ItemStack> getDroppedItems(World world, int x, int y, int z, int meta, int fortune) {
        ArrayList<ItemStack> list = new ArrayList<ItemStack>();
        List<Item> allItems = getAllItems();
        int num = rand.nextInt(3);
        for (int i = 0; i < num; i++) {
            Item item = allItems.get(rand.nextInt(allItems.size()));
            list.add(new ItemStack(item, rand.nextInt(5)));
        }
        return list;
    }

    private List<Item> getAllItems() {
        return Arrays.asList(Item.getItemFromBlock(Blocks.netherrack), Item.getItemFromBlock(Blocks.nether_wart), Item.getItemFromBlock(Blocks.obsidian), Items.fire_charge, Items.blaze_rod, Items.magma_cream);
    }

    @Override
	public String getName() {
		return "pandaemonium";
	}
}
