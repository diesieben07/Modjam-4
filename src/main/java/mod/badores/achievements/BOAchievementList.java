package mod.badores.achievements;

import mod.badores.BadOres;
import mod.badores.blocks.BlockBadOre;
import mod.badores.items.ItemBOIngot;
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
    public static Achievement killedNosleeptonite;
	public static Achievement allOres;
    public static Achievement minedKillium;
	public static Achievement getShiftium;
    public static Achievement madeMarmiteBread;
    public static Achievement minedZombieunite;
    public static Achievement smeltedFleesonsite;

    private static ItemStack getIngotStack(String oreID) {
        return ItemBOIngot.createIngot(BadOres.oreManager.getOreByName(oreID));
    }

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
        killedNosleeptonite = new Achievement("achievement.killedNosleeptonite", "killedNosleeptonite", 2, -2, getOreStack("nosleeptonite"), null).registerStat();
	    allOres = new Achievement("achievement.allBadOres", "allBadOres", 4, 4, new ItemStack(BadOres.badOreBook), null).registerStat();
        minedKillium = new Achievement("achievement.minedKillium", "minedKillium", 4, -1, getOreStack("killium"), null).registerStat();
        madeMarmiteBread = new Achievement("achievement.madeMarmiteBread", "madeMarmiteBread", 6, 1, new ItemStack(BadOres.marmiteBread), null).registerStat();
        minedZombieunite = new Achievement("achievement.minedZombieunite", "minedZombieunite", 1, 4, getOreStack("zombieunite"), null).registerStat();
        smeltedFleesonsite = new Achievement("achievement.smeltedFleesonsite", "smeltedFleesonsite", -2, 1, getIngotStack("fleesonsite"), null).registerStat();
	    getShiftium = new Achievement("achievement.getShifitium", "getShiftium", -4, 1, getOreStack("shiftium"), null).registerStat();

	    page = new AchievementPage(BadOres.NAME, barelyGeneriteFound, buildBarelyGeneriteBlock, iwontfiteDamage, killedNosleeptonite, allOres, minedKillium, madeMarmiteBread, minedZombieunite, smeltedFleesonsite, getShiftium);
	    AchievementPage.registerAchievementPage(page);
    }
}
