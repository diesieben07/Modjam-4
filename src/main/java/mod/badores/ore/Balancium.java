package mod.badores.ore;

import mod.badores.util.JavaUtils;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.Arrays;
import java.util.List;

/**
 * @author diesieben07
 */
public class Balancium extends AbstractOre {

	public static final List<Item> items = Arrays.asList(Items.diamond, Items.emerald, Items.gold_ingot, Items.iron_ingot, Items.coal, Items.quartz, Items.golden_apple);

	@Override
	public String getName() {
		return "balancium";
	}

    @Override
    protected int veinSize() {
        return 3;
    }

    @Override
	public void addDroppedItems(World world, int x, int y, int z, int meta, int fortune, List<ItemStack> drops, boolean isIngotBlock) {
	    int num = rand.nextInt(50) + 5;
		for (int i = 0; i < num; i++) {
			Item item = JavaUtils.selectRandom(rand, items);
			drops.add(new ItemStack(item, 1));
		}
	}

}
