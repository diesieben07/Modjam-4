package mod.badores.achievements;

import mod.badores.BadOres;
import mod.badores.blocks.BlockBadOre;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;

/**
 * Created by Lukas Tenbrink on 18.05.2014.
 */
public class BOAchievementList {

    public static Achievement buildBarelyGeneritesBlock;

    private static ItemStack getOreStack(String oreID) {
        return BadOres.oreManager.getBlockInfo(BadOres.oreManager.getOreByName(oreID)).asStack();
    }

    private static ItemStack getOreBlockStack(String oreID) {
        return BlockBadOre.createIngotBlock(BadOres.oreManager.getOreByName(oreID));
    }

    public static void init() {
        buildBarelyGeneritesBlock = (new Achievement("achievement.buildBarelyGeneritesBlock", "buildBarelyGeneritesBlock", 4, 8, getOreBlockStack("barelyGenerites"), null)).registerStat();
    }
}
