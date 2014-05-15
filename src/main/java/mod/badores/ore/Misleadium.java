package mod.badores.ore;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author diesieben07
 */
public class Misleadium extends AbstractOre {

    private static final int NUM_MESSAGES = 7;

    private static final int SIDE_RANGE = 500;

    @Override
	public void onHarvest(EntityPlayer miner, World world, int x, int y, int z, Side side) {
		if (side.isServer()) {
            int msg = world.rand.nextInt(NUM_MESSAGES);

            List<ItemStack> itemList = new ArrayList<ItemStack>();
            for (Object anItemRegistry : Item.itemRegistry) {
                Item item = (Item) anItemRegistry;

                if (item != null && item.getCreativeTab() != null) {
                    item.getSubItems(item, null, itemList);
                }
            }
            ItemStack item = itemList.get(rand.nextInt(itemList.size()));

            int fX = (x + rand.nextInt(SIDE_RANGE) - rand.nextInt(SIDE_RANGE));
            int fY = (rand.nextInt(100) + 10);
            int fZ = (z + rand.nextInt(SIDE_RANGE) - rand.nextInt(SIDE_RANGE));

			miner.addChatComponentMessage(new ChatComponentTranslation("badores.misleadium.baseMessage." + msg, item.func_151000_E(), "" + fX, "" + fY, "" + fZ));
		}
	}

    @Override
    public String getName() {
        return "misleadium";
    }
}
