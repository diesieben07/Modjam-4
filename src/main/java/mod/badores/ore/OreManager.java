package mod.badores.ore;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Lists;
import mod.badores.blocks.BlockBadOre;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

/**
 * @author diesieben07
 */
public final class OreManager {

	private List<BadOre> allOres = Lists.newArrayList();
	private final BiMap<BlockInfo, BadOre> ores = HashBiMap.create();

	public void registerOre(BadOre ore) {
		checkState(allOres != null, "Attempted to register ore after startup!");
		allOres.add(checkNotNull(ore, "ore"));
	}

	public void createBlocks() {
		BlockBadOre currentBlock = null;
		int currentMetadata = 0;
		for (BadOre ore : allOres) {
			if (currentBlock == null) {
				currentBlock = new BlockBadOre();
			}
			currentBlock.addOre(currentMetadata, ore);
			ores.put(new BlockInfo(currentBlock, currentMetadata), ore);

			if (++currentMetadata == 16) {
				currentBlock = null;
				currentMetadata = 0;
			}
		}
	}

}
