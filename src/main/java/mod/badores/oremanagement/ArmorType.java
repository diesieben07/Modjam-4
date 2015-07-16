package mod.badores.oremanagement;

import cpw.mods.fml.common.registry.GameRegistry;
import mod.badores.util.I18n;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * @author diesieben07
 */
public enum ArmorType implements OreSubName {

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
    public final int vanillaID;

    ArmorType(String name, int vanillaID) {
        this.name = name;
        this.vanillaID = vanillaID;
    }

    public int getLayer() {
        return this == LEGGINGS ? 2 : 1;
    }

    abstract void registerRecipe(Item result, ItemStack input);

    @Override
    public String subName(String translatedOreName) {
        return I18n.translateBO(name, translatedOreName);
    }
}
