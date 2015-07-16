package mod.badores.ore;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import cpw.mods.fml.relauncher.Side;
import mod.badores.BadOres;
import mod.badores.network.PacketRandomTranslation;
import mod.badores.util.FakePlayerDetection;
import mod.badores.util.JavaUtils;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.List;

/**
 * @author diesieben07
 */
public class Misleadium extends AbstractOre {

	private static final int SIDE_RANGE = 500;
	private List<ItemStack> cache;

	@Override
	public void onHarvest(EntityPlayer miner, World world, int x, int y, int z, Side side, boolean isIngotBlock) {
		if (FakePlayerDetection.isFakePlayer(miner)) return;
		if (side.isServer()) {
			int numItems = Item.itemRegistry.getKeys().size();
			Item item;
			List<ItemStack> cache = (this.cache == null) ? (this.cache = Lists.newArrayList()) : this.cache;
			cache.clear();
			do {
				do {
					int itemIdx = rand.nextInt(numItems);
					item = (Item) Iterables.get(Item.itemRegistry, itemIdx);
				} while (item.getCreativeTab() == null);
				item.getSubItems(item, CreativeTabs.tabAllSearch, cache);
			} while (cache.isEmpty());

			ItemStack stack = JavaUtils.selectRandom(rand, cache);

			int fX = (x + rand.nextInt(SIDE_RANGE) - rand.nextInt(SIDE_RANGE));
			int fY = (rand.nextInt(100) + 10);
			int fZ = (z + rand.nextInt(SIDE_RANGE) - rand.nextInt(SIDE_RANGE));

			BadOres.network.sendTo(new PacketRandomTranslation("badores.misleadium.baseMessage", stack.getDisplayName(), Integer.toString(fX), Integer.toString(fY), Integer.toString(fZ)), ((EntityPlayerMP) miner));
		}
	} 

	@Override
	public String getName() {
		return "misleadium";
	}
}
