package mod.badores;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import mod.badores.items.ItemBOIngot;
import mod.badores.ore.*;
import mod.badores.worldgen.WorldGeneratorBadOres;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.launchwrapper.Launch;
import net.minecraftforge.common.config.Configuration;
import org.apache.logging.log4j.Logger;

/**
 * @author diesieben07
 */
@Mod(modid = BadOres.MOD_ID, name = "BadOres", version = BadOres.VERSION)
public class BadOres {

	public static final String MOD_ID = "badores";
    public static final String VERSION = "0.1";

	@SidedProxy(clientSide = "mod.badores.client.BOClientProxy", serverSide = "mod.badores.server.BOServerProxy")
	public static BOProxy proxy;

    public static Logger logger;

	public static boolean devEnv = (Boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment");

    public static boolean enableGameBreakingFeatures;

	public static CreativeTabs creativeTab = new CreativeTabs("badores") {
		@Override
		public Item getTabIconItem() {
			// TODO
			return Items.apple;
		}
	};

	public static OreManager oreManager;

    public static ItemBOIngot ingot;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        Configuration config = new Configuration(event.getSuggestedConfigurationFile());
        config.load();

        enableGameBreakingFeatures = config.get("balancing", "enableGameBreakingFeatures", true).getBoolean(true); // TODO implement

		oreManager = new OreManager();

		oreManager.registerOre(new Polite());
		oreManager.registerOre(new Wannafite());
        oreManager.registerOre(new Breakium());
        oreManager.registerOre(new Crashium());
        oreManager.registerOre(new Stonium());
		oreManager.registerOre(new Crappium());
		oreManager.registerOre(new Enderite());
		oreManager.registerOre(new Website());
        oreManager.registerOre(new Lite());
        oreManager.registerOre(new Misleadium());
        oreManager.registerOre(new Ghostium());
        oreManager.registerOre(new Amadeum());
        oreManager.registerOre(new IncrediblyRarium());
        oreManager.registerOre(new Unobtainium());
        oreManager.registerOre(new Copper());
        oreManager.registerOre(new Uselessium());
        oreManager.registerOre(new Killium());

		ingot = new ItemBOIngot();
		GameRegistry.registerItem(ingot, "ingot");

		oreManager.createGameElements();

		proxy.preInit(event, config);
        config.save();
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
        GameRegistry.registerWorldGenerator(new WorldGeneratorBadOres(oreManager), 100);

        proxy.init(event);
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		proxy.postInit(event);
	}

	public static String translateKey(String name) {
		return MOD_ID + "." + name;
	}

	public static String getTextureName(String name) {
		return MOD_ID + ":" + name;
	}
}
