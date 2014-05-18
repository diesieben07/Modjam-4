package mod.badores;

import com.google.common.collect.Maps;
import gnu.trove.iterator.TLongObjectIterator;
import gnu.trove.map.TLongObjectMap;
import gnu.trove.map.hash.TLongObjectHashMap;
import mod.badores.blocks.TickingBlock;
import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.util.Constants;

import java.util.Map;

/**
 * @author diesieben07
 */
public class BlockTicker extends WorldSavedData {

	public static final String IDENTIFIER = "badores.blockticks";

	private final TLongObjectMap<ScheduledTick> ticks = new TLongObjectHashMap<>();

	public BlockTicker() {
		super(IDENTIFIER);
	}

	public BlockTicker(String identifier) {
		super(identifier);
	}

	public static void schedule(World world, int x, int y, int z, TickingBlock block, int delay) {
		get(world).schedule0(world, x, y, z, block, delay);
	}

	private void schedule0(World world, int x, int y, int z, TickingBlock block, int delay) {
		long worldTick = world.getTotalWorldTime();
		long when = worldTick + delay;

		ScheduledTick tick = new ScheduledTick(block, x, y, z);

		ScheduledTick prev = ticks.get(when);
		if (prev != null) {
			prev.next = tick;
		}
		ticks.put(when, tick);
		markDirty();
	}

	private static Map<World, BlockTicker> tickers = Maps.newHashMapWithExpectedSize(DimensionManager.getIDs().length);

	public static BlockTicker get(World world) {
		BlockTicker ticker = tickers.get(world);
		if (ticker == null) {
			ticker = (BlockTicker) world.perWorldStorage.loadData(BlockTicker.class, IDENTIFIER);
			if (ticker == null) {
				world.perWorldStorage.setData(IDENTIFIER, (ticker = new BlockTicker()));
			}
			tickers.put(world, ticker);
		}
		return ticker;
	}

	public static void unload(World world) {
		tickers.remove(world);
	}

	public void tick(World world) {
		ScheduledTick tick = ticks.remove(world.getTotalWorldTime());
		while (tick != null) {
			tick.tick(world);
			tick = tick.next;
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		ticks.clear();

		NBTTagList list = nbt.getTagList("ticks", Constants.NBT.TAG_COMPOUND);
		int len = list.tagCount();
		for (int i = 0; i < len; ++i) {
			NBTTagCompound tag = list.getCompoundTagAt(i);
			long when = tag.getLong("w");
			ScheduledTick tick = ScheduledTick.read(tag.getTagList("t", Constants.NBT.TAG_COMPOUND));
			ticks.put(when, tick);
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		NBTTagList list = new NBTTagList();
		for (TLongObjectIterator<ScheduledTick> it = ticks.iterator(); it.hasNext();) {
			it.advance();
			NBTTagCompound tag = new NBTTagCompound();
			tag.setLong("w", it.key());
			NBTTagList tickList = new NBTTagList();
			it.value().write(tickList);
			tag.setTag("t", tickList);
			list.appendTag(tag);
		}
		nbt.setTag("ticks", list);
	}

	private static class ScheduledTick {

		final TickingBlock block;
		final int x, y, z;
		ScheduledTick next;

		ScheduledTick(TickingBlock block, int x, int y, int z) {
			this.block = block;
			this.x = x;
			this.y = y;
			this.z = z;
		}

		public void tick(World world) {
			if (world.getBlock(x, y, z) == block) {
				block.tick(world, x, y, z);
			}
		}

		void write(NBTTagList list) {
			ScheduledTick t = this;
			do {
				NBTTagCompound nbt = new NBTTagCompound();
				nbt.setShort("b", (short) Block.getIdFromBlock((Block) t.block));
				nbt.setInteger("x", t.x);
				nbt.setInteger("y", t.y);
				nbt.setInteger("z", t.z);
				list.appendTag(nbt);
				t = next;
			} while (t != null);
		}

		static ScheduledTick read(NBTTagList list) {
			int len = list.tagCount();
			ScheduledTick head = null;
			ScheduledTick last = null;

			for (int i = 0; i < len; ++i) {
				NBTTagCompound tag = list.getCompoundTagAt(i);
				Block b = Block.getBlockById(tag.getShort("b"));
				if (!(b instanceof TickingBlock)) {
					continue;
				}
				int x = tag.getInteger("x");
				int y = tag.getInteger("y");
				int z = tag.getInteger("z");

				ScheduledTick tick = new ScheduledTick((TickingBlock) b, x, y, z);
				if (head == null) {
					head = last = tick;
				} else {
					tick.next = last;
					last = tick;
				}
			}
			return head;
		}
	}

}
