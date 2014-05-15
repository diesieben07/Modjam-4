package mod.badores.ore;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import cpw.mods.fml.common.registry.GameRegistry;
import mod.badores.BadOres;
import mod.badores.blocks.BlockBadOre;
import mod.badores.items.*;
import net.minecraft.init.Items;
import net.minecraft.item.*;
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

	private ItemBOIngot currentIngot;
	private int currentIngotMeta;

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
			oreNames.put(ore.getName(), ore);

			ItemStack craftingInput = getOreCraftingInput(ore, blockInfo);

			if (ore.canMakeTools()) {
				generateTools(ore, craftingInput);
			}

            if (ore.canMakeArmor()) {
                generateArmor(ore, craftingInput);
            }

			if (++currentMetadata == 16) {
				currentBlock = null;
				currentMetadata = 0;
			}
		}
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

	private void newToolItem(Item i, BadOre ore, ItemStack toolInput, ToolType type) {
		i.setTextureName(getTextureName(ore.getName()) + "_" + type.name);

		String n = ore.getName() + "." + type.name;
		i.setUnlocalizedName("badores." + n);

		i.setCreativeTab(BadOres.creativeTab);

		GameRegistry.registerItem(i, n);
		type.registerRecipe(i, toolInput);
	}

    private void newArmorItem(Item i, BadOre ore, ItemStack recipeInput, ArmorType armorType) {
        i.setTextureName(getTextureName(ore.getName()) + "_" + armorType.name);

        String n = ore.getName() + "." + armorType.name;
        i.setUnlocalizedName("badores." + n);

        i.setCreativeTab(BadOres.creativeTab);

        GameRegistry.registerItem(i, n);
        armorType.registerRecipe(i, recipeInput);
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

    public static enum ToolType {

		HOE("hoe") {
			@Override
			void registerRecipe(Item result, ItemStack input) {
				GameRegistry.addRecipe(new ItemStack(result), "XX", " |", " |", 'X', input, '|', Items.stick);
			}
		},
		SHOVEL("shovel") {
			@Override
			void registerRecipe(Item result, ItemStack input) {
				GameRegistry.addRecipe(new ItemStack(result), "X", "|", "|", 'X', input, '|', Items.stick);
			}
		},
		PICKAXE("pickaxe") {
			@Override
			void registerRecipe(Item result, ItemStack input) {
				GameRegistry.addRecipe(new ItemStack(result), "XXX", " | ", " | ", 'X', input, '|', Items.stick);
			}
		},
		AXE("axe") {
			@Override
			void registerRecipe(Item result, ItemStack input) {
				GameRegistry.addRecipe(new ItemStack(result), "XX", "X|", " |", 'X', input, '|', Items.stick);
			}
		},
		SWORD("sword") {
			@Override
			void registerRecipe(Item result, ItemStack input) {
				GameRegistry.addRecipe(new ItemStack(result), "X", "X", "|", 'X', input, '|', Items.stick);
			}
		};

		final String name;

		ToolType(String name) {
			this.name = name;
		}

		abstract void registerRecipe(Item result, ItemStack input);
	}

    public static enum ArmorType {

        HELMET("helmet", 0) {
            @Override
            void registerRecipe(Item result, ItemStack input) {
                GameRegistry.addRecipe(new ItemStack(result), "XXX", "X X", "   ", 'X', input);
            }
        },
        CHESTPLATE("chestplate", 1) {
            @Override
            void registerRecipe(Item result, ItemStack input) {
                GameRegistry.addRecipe(new ItemStack(result), "X X", "XXX", "XXX", 'X', input);
            }
        },
        LEGGINGS("leggings", 2) {
            @Override
            void registerRecipe(Item result, ItemStack input) {
                GameRegistry.addRecipe(new ItemStack(result), "XXX", "X X", "X X", 'X', input);
            }
        },
        BOOTS("boots", 3) {
            @Override
            void registerRecipe(Item result, ItemStack input) {
                GameRegistry.addRecipe(new ItemStack(result), "   ", "X X", "X X", 'X', input);
            }
        };

        public final String name;
        public final int armorType;

        ArmorType(String name, int armorType) {
            this.name = name;
            this.armorType = armorType;
        }

        abstract void registerRecipe(Item result, ItemStack input);
    }
}