package mod.badores.oremanagement;

import cpw.mods.fml.common.registry.GameRegistry;
import mod.badores.util.I18n;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * @author diesieben07
 */
public enum ToolType implements OreSubName {

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

    private ToolType(String name) {
        this.name = name;
    }

    abstract void registerRecipe(Item result, ItemStack input);

    @Override
    public String subName(String translatedOreName) {
        return I18n.translateBO(name, translatedOreName);
    }
}
