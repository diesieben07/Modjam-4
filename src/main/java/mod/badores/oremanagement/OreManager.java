package mod.badores.oremanagement;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import mod.badores.BadOres;
import mod.badores.blocks.BlockBadOre;
import mod.badores.items.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;

import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;
import static mod.badores.BadOres.getTextureName;

/**
 * @author diesieben07
 */
public final class OreManager {

	private List<BadOre> allOres = Lists.newArrayList();
	private final BiMap<BlockInfo, BadOre> ores = HashBiMap.create();
	private final Map<String, BadOre> oreNames = Maps.newHashMap();

	private int requiredCount = 0;
	private boolean initialized;

	/**
	 * Use this method during a mod's Pre-Initialization event.
	 * Make sure the mod loads BEFORE badores
	 */
	public void registerOre(BadOre ore) {
		checkState(!initialized, "Attempted to register ore after startup!");
		checkNotNull(ore, "ore");
		allOres.add(ore);
		oreNames.put(ore.getName(), ore);
	}

	public void createGameElements() {
		initialized = true;
		BlockBadOre currentBlock = null;
		int currentMetadata = 0;

		for (BadOre ore : allOres) {
			if (currentBlock == null) {
				currentBlock = new BlockBadOre();
			}
			currentBlock.addOre(currentMetadata, ore);

			BlockInfo blockInfo = new BlockInfo(currentBlock, currentMetadata);
			ores.put(blockInfo, ore);

			ItemStack craftingInput = getOreCraftingInput(ore, blockInfo);

			if (ore.hasIngot() && !ore.dropsIngotDirectly()) {
				addSmelting(ore, blockInfo);
			}

			if (ore.hasIngot() && ore.hasIngotBlock()) {
				addIngotBlockRecipe(ore);
			}

			if (ore.hasTools()) {
				generateTools(ore, craftingInput);
			}

			if (ore.hasArmor()) {
				generateArmor(ore, craftingInput);
			}

			if (ore.requiredForAll()) {
				requiredCount++;
			}

			if (++currentMetadata == BlockBadOre.MAX_ORES_PER_BLOCK) {
				currentBlock = null;
				currentMetadata = 0;
			}
		}
	}

	public int getRequiredCount() {
		return requiredCount;
	}

	private void addIngotBlockRecipe(BadOre ore) {
		GameRegistry.addRecipe(BlockBadOre.createIngotBlock(ore),
				"XXX", "XXX", "XXX",
				'X', ItemBOIngot.createIngot(ore));

		ItemStack ingots = ItemBOIngot.createIngot(ore);
		ingots.stackSize = 9;
		GameRegistry.addShapelessRecipe(ingots, BlockBadOre.createIngotBlock(ore));
	}

	private void addSmelting(BadOre ore, BlockInfo block) {
		GameRegistry.addSmelting(block.asStack(), ItemBOIngot.createIngot(ore), ore.getSmeltingXP());
	}

	private ItemStack getOreCraftingInput(BadOre ore, BlockInfo blockInfo) {
		ItemStack toolInput;
		if (ore.hasIngot()) {
			toolInput = new ItemStack(BadOres.ingot);
			ItemBOIngot.setOre(toolInput, ore);
		} else {
			toolInput = blockInfo.asStack();
		}
		return toolInput;
	}

	private void generateTools(BadOre ore, ItemStack toolInput) {
		String tmName = "BAD_ORE_" + ore.getName();
		ToolInfo toolData = ore.getToolInfo();
		Item.ToolMaterial toolMaterial = EnumHelper.addToolMaterial(tmName, toolData.harvestLevel, toolData.maxUses, toolData.efficiency, toolData.damage, toolData.enchantability);

		newToolItem(new ItemBOHoe(toolMaterial, ore), ore, toolInput, ToolType.HOE);
		newToolItem(new ItemBOShovel(toolMaterial, ore), ore, toolInput, ToolType.SHOVEL);
		newToolItem(new ItemBOPickaxe(toolMaterial, ore), ore, toolInput, ToolType.PICKAXE);
		newToolItem(new ItemBOAxe(toolMaterial, ore), ore, toolInput, ToolType.AXE);
		newToolItem(new ItemBOSword(toolMaterial, ore), ore, toolInput, ToolType.SWORD);
	}

	private void generateArmor(BadOre ore, ItemStack recipeInput) {
		String amName = "BAD_ORE_" + ore.getName();
		ArmorInfo armorData = ore.getArmorInfo();
		ItemArmor.ArmorMaterial armorMaterial = EnumHelper.addArmorMaterial(amName, armorData.durability, armorData.reductionAmounts, armorData.enchantability);

		newArmorItem(new ItemBOArmor(armorMaterial, ArmorType.HELMET, ore), ore, recipeInput, ArmorType.HELMET);
		newArmorItem(new ItemBOArmor(armorMaterial, ArmorType.CHESTPLATE, ore), ore, recipeInput, ArmorType.CHESTPLATE);
		newArmorItem(new ItemBOArmor(armorMaterial, ArmorType.LEGGINGS, ore), ore, recipeInput, ArmorType.LEGGINGS);
		newArmorItem(new ItemBOArmor(armorMaterial, ArmorType.BOOTS, ore), ore, recipeInput, ArmorType.BOOTS);
	}

	private <T extends Item & BOOreProduct> void newToolItem(T i, BadOre ore, ItemStack toolInput, ToolType type) {
		i.setTextureName(getTextureName(ore.getName()) + "." + type.name);

		String n = ore.getName() + "." + type.name;
		i.setUnlocalizedName("badores." + n);

		i.setCreativeTab(BadOres.creativeTab);

		GameRegistry.registerItem(i, n);
		type.registerRecipe(i, toolInput);
		ore.postProcessItem(i, OreForm.fromTool(type));
	}

	private <T extends Item & BOOreProduct> void newArmorItem(T i, BadOre ore, ItemStack recipeInput, ArmorType armorType) {
		i.setTextureName(getTextureName(ore.getName()) + "." + armorType.name);

		String n = ore.getName() + "." + armorType.name;
		i.setUnlocalizedName("badores." + n);

		i.setCreativeTab(BadOres.creativeTab);

		GameRegistry.registerItem(i, n);
		armorType.registerRecipe(i, recipeInput);
		ore.postProcessItem(i, OreForm.fromArmor(armorType));
	}

	public List<BadOre> getAllOres() {
		return allOres;
	}

	public BlockInfo getBlockInfo(BadOre ore) {
		return ores.inverse().get(ore);
	}

	public BadOre getOreByName(String name) {
		return oreNames.get(name);
	}

}