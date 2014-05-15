package mod.badores.ore;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Lists;
import cpw.mods.fml.common.registry.GameRegistry;
import mod.badores.BadOres;
import mod.badores.blocks.BlockBadOre;
import mod.badores.items.ItemBOPickaxe;
import mod.badores.items.ItemBOAxe;
import net.minecraft.init.Items;
import net.minecraft.item.*;
import net.minecraftforge.common.util.EnumHelper;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;
import static mod.badores.BadOres.getTextureName;

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

	public void createGameElements() {
		BlockBadOre currentBlock = null;
		int currentMetadata = 0;
		for (BadOre ore : allOres) {
			if (currentBlock == null) {
				currentBlock = new BlockBadOre();
			}
			currentBlock.addOre(currentMetadata, ore);
			BlockInfo blockInfo = new BlockInfo(currentBlock, currentMetadata);
			ores.put(blockInfo, ore);

			if (ore.canMakeTools()) {
				generateTools(ore, blockInfo);
			}

			if (++currentMetadata == 16) {
				currentBlock = null;
				currentMetadata = 0;
			}
		}
	}

	private void generateTools(BadOre ore, BlockInfo oreBlock) {
		String tmName = "BAD_ORE_" + ore.getName();
		ToolInfo toolData = ore.getToolInfo();
		Item.ToolMaterial toolMaterial = EnumHelper.addToolMaterial(tmName, toolData.harvestLevel, toolData.maxUses, toolData.efficiency, toolData.damage, toolData.enchantability);

		String texture = getTextureName(ore.getName());
		String name = "badores." + ore.getName() + ".";

		newItem(new ItemHoe(toolMaterial), ore, oreBlock, ToolType.HOE);
		newItem(new ItemSpade(toolMaterial), ore, oreBlock, ToolType.SHOVEL);
		newItem(new ItemBOPickaxe(toolMaterial), ore, oreBlock, ToolType.PICKAXE);
		newItem(new ItemBOAxe(toolMaterial), ore, oreBlock, ToolType.AXE);
		newItem(new ItemSword(toolMaterial), ore, oreBlock, ToolType.SWORD);
	}

	private void newItem(Item i, BadOre ore, BlockInfo blockInfo, ToolType type) {
		i.setTextureName(getTextureName(ore.getName()) + "_" + type.name);

		String n = ore.getName() + "." + type.name;
		i.setUnlocalizedName("badores." + n);

		i.setCreativeTab(BadOres.creativeTab);

		GameRegistry.registerItem(i, n);
		type.registerRecipe(i, blockInfo);
	}

	public List<BadOre> getAllOres() {
		return allOres;
	}

	public BlockInfo getBlockInfo(BadOre ore) {
		return ores.inverse().get(ore);
	}

	private static enum ToolType {

		HOE("hoe") {
			@Override
			void registerRecipe(Item result, BlockInfo oreBlock) {
				ItemStack ore = oreBlock.asStack();
				GameRegistry.addRecipe(new ItemStack(result), "XX", " |", " |", 'X', ore, '|', Items.stick);
			}
		},
		SHOVEL("shovel") {
			@Override
			void registerRecipe(Item result, BlockInfo oreBlock) {
				ItemStack ore = oreBlock.asStack();
				GameRegistry.addRecipe(new ItemStack(result), "X", "|", "|", 'X', ore, '|', Items.stick);
			}
		},
		PICKAXE("pickaxe") {
			@Override
			void registerRecipe(Item result, BlockInfo oreBlock) {
				ItemStack ore = oreBlock.asStack();
				GameRegistry.addRecipe(new ItemStack(result), "XXX", " | ", " | ", 'X', ore, '|', Items.stick);
			}
		},
		AXE("axe") {
			@Override
			void registerRecipe(Item result, BlockInfo oreBlock) {
				ItemStack ore = oreBlock.asStack();
				GameRegistry.addRecipe(new ItemStack(result), "XX", "X|", " |", 'X', ore, '|', Items.stick);
			}
		},
		SWORD("sword") {
			@Override
			void registerRecipe(Item result, BlockInfo oreBlock) {
				ItemStack ore = oreBlock.asStack();
				GameRegistry.addRecipe(new ItemStack(result), "X", "X", "|", 'X', ore, '|', Items.stick);
			}
		};

		final String name;

		ToolType(String name) {
			this.name = name;
		}

		abstract void registerRecipe(Item result, BlockInfo oreBlock);
	}

}