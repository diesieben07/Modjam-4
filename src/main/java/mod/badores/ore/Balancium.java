package mod.badores.ore;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author diesieben07
 */
public class Balancium extends AbstractOre {

	@Override
	public String getName() {
		return "balancium";
	}

    @Override
    public List<ItemStack> getDroppedItems(World world, int x, int y, int z, Block block, int meta, int fortune) {
        ArrayList<ItemStack> list = new ArrayList<ItemStack>();
        List<Item> allItems = getAllItems();
        int num = rand.nextInt(50) + 5;
        for (int i = 0; i < num; i++) {
            Item item = allItems.get(rand.nextInt(allItems.size()));
            list.add(new ItemStack(item, 1));
        }
        return list;
    }

    private List<Item> getAllItems()
    {
        return Arrays.asList(Items.diamond, Items.emerald, Items.gold_ingot, Items.iron_ingot, Items.coal, Items.quartz, Items.golden_apple);
    }
}
