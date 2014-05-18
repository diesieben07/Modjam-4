package mod.badores.achievements;

import mod.badores.BadOres;
import mod.badores.blocks.BlockBadOre;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;

/**
 * Created by Lukas Tenbrink on 18.05.2014.
 */
public class BOAchievementList {

	public static AchievementPage page;

    public static Achievement barelyGeneriteFound;
    public static Achievement buildBarelyGeneriteBlock;
	public static Achievement iwontfiteDamage;

    private static ItemStack getOreStack(String oreID) {
        return BadOres.oreManager.getBlockInfo(BadOres.oreManager.getOreByName(oreID)).asStack();
    }

    private static ItemStack getOreBlockStack(String oreID) {
        return BlockBadOre.createIngotBlock(BadOres.oreManager.getOreByName(oreID));
    }

    public static void init() {
        barelyGeneriteFound = (new Achievement("achievement.barelyGeneriteFound", "barelyGeneriteFound", 0, 2, getOreStack("barelyGenerite"), null)).registerStat();
        buildBarelyGeneriteBlock = new Achievement("achievement.buildBarelyGeneriteBlock", "buildBarelyGeneriteBlock", 3, 2, getOreBlockStack("barelyGenerite"), barelyGeneriteFound).registerStat();
	    iwontfiteDamage = new Achievement("achievement.iwontfiteDamage", "iwontfiteDamage", 0, 0, getOreStack("iwontfite"), null).registerStat();

	    page = new AchievementPage(BadOres.NAME, barelyGeneriteFound, buildBarelyGeneriteBlock, iwontfiteDamage);
	    AchievementPage.registerAchievementPage(page);
    }
}
